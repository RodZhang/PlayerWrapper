package com.rod.mediaplayerwrapper;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.rod.BasePlayer;
import com.rod.annotation.PlayerState;
import com.rod.command.Command;
import com.rod.command.CommandInvoker;
import com.rod.command.Pause;
import com.rod.command.PrepareAsync;
import com.rod.command.Release;
import com.rod.command.Reset;
import com.rod.command.SeekTo;
import com.rod.command.SetDataSource;
import com.rod.command.Start;
import com.rod.log.PL;
import com.rod.state.StateContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rod
 * @date 2018/7/14
 */
public class SafeMediaPlayer extends BasePlayer {
    private static final String TAG = "SafeMediaPlayer";
    private final MediaPlayer mMediaPlayer = new MediaPlayer();
    private final CommandInvoker mCommandInvoker = new CommandInvoker();
    private final List<Command> mPendingCommands = new ArrayList<>();
    private final Handler mHandler;
    private boolean mIsEnable = false;
    private boolean mIsPlayingOnStartSeek;
    private final StateContext mStateContext;

    public SafeMediaPlayer() {
        mHandler = new Handler(Looper.getMainLooper());
        mStateContext = new StateContext();
        mStateContext.setOnStateChangedListener(newState -> {
            mHandler.post(() -> {
                dispatchOnStateChanged(newState);
            });
        });
        mCommandInvoker.attach(mStateContext);
        mStateContext.attach(mMediaPlayer, mCommandInvoker);
        initListeners();
    }

    private void initListeners() {
        mMediaPlayer.setOnErrorListener((mp, what, extra) -> {
            PL.d(TAG, "on error, what=%d, extra=%d", what, extra);
            dispatchOnStateChanged(PlayerState.ERROR);
            return false;
        });
        mMediaPlayer.setOnBufferingUpdateListener((mp, percent) -> {
            int currentPosition = getCurrentPosition();
            int duration = getDuration();
            PL.d(TAG, "on buffering updated, percent=%d, curPos=%d, duration=%d", percent, currentPosition, duration);
            dispatchOnBufferChanged(percent, duration);
        });
        mMediaPlayer.setOnVideoSizeChangedListener((mp, width, height) -> {
            PL.d(TAG, "onVideoSizeChanged, width=%d, height=%d", width, height);
        });
        mMediaPlayer.setOnInfoListener((mp, what, extra) -> {
            PL.d(TAG, "on info, what=%d, extra=%d", what, extra);
            switch (what) {
                case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:
                    dispatchOnStateChanged(PlayerState.PLAYING);
                    break;
                case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                    dispatchOnStateChanged(PlayerState.BUFFER_START);
                    break;
                case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                    dispatchOnStateChanged(PlayerState.BUFFER_END);
                default:
                    break;
            }
            return true;
        });
        mMediaPlayer.setOnCompletionListener(mp -> {
            int currentPosition = getCurrentPosition();
            int duration = getDuration();
            PL.d(TAG, "on complete, curPos=%d, duration=%d", currentPosition, duration);
            mStateContext.onComplete();
        });

        setTimerListener(() -> {
            int currentPosition = getCurrentPosition();
            int duration = getDuration();
            PL.d(TAG, "on buffering updated, curPos=%d, duration=%d", currentPosition, duration);
            dispatchProgressChanged(currentPosition, duration);
        });
    }

    @Override
    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        PL.d(TAG, "setSurfaceHolder");
        mMediaPlayer.setDisplay(surfaceHolder);
        onPlayerEnable();
    }

    @Override
    public void setSurface(Surface surface) {
        mMediaPlayer.setSurface(surface);
        onPlayerEnable();
    }

    @Override
    public void onSurfaceDestroyed() {
    }

    @Override
    public void setSource(String url) {
        super.setSource(url);
    }

    @Override
    public void play(String url) {
        PL.d(TAG, "play: %s", url);
        if (!TextUtils.equals(url, mUrl)) {
            PL.d(TAG, "play: url is not equals");
            setSource(url);
            playSourceInit(url);
            return;
        }
        if (mMediaPlayer.isPlaying()) {
            PL.d(TAG, "play: mMediaPlayer is playing");
            return;
        }
        List<Command> commands = new ArrayList<>();
        commands.add(new Start());
        commitCommands(commands);
    }

    @Override
    public void pause() {
        if (!mMediaPlayer.isPlaying()) {
            PL.i(TAG, "pause, mediaPlayer is not playing, return");
            return;
        }
        List<Command> commands = new ArrayList<>();
        commands.add(new Pause());
        commitCommands(commands);
    }

    @Override
    public void release() {
        PL.d(TAG, "release");
        detachFromContainer();
        List<Command> commands = new ArrayList<>();
        commands.add(new Release());
        commitCommands(commands);
    }

    @Override
    public boolean isPlaying() {
        return mMediaPlayer.isPlaying();
    }

    @Override
    public void onStartSeek() {
        if (mMediaPlayer.isPlaying()) {
            List<Command> commands = new ArrayList<>();
            commands.add(new Pause());
            mCommandInvoker.commit(commands);
            mIsPlayingOnStartSeek = true;
        } else {
            mIsPlayingOnStartSeek = false;
        }
    }

    @Override
    public void onEndSeek(int progress) {
        List<Command> commands = new ArrayList<>();
        commands.add(new SeekTo(progress));
        if (mIsPlayingOnStartSeek) {
            commands.add(new Start());
            mCommandInvoker.commit(commands);
        }
    }

    @Override
    public int getCurrentPosition() {
        return mStateContext.getCurrentPosition();
    }

    @Override
    public int getDuration() {
        return mStateContext.getDuration();
    }

    @Override
    public int getVideoHeight() {
        return mStateContext.getVideoHeight();
    }

    @Override
    public int getVideoWidth() {
        return mStateContext.getVideoWidth();
    }

    @Override
    public void setAudioAttributes(AudioAttributes attributes) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mStateContext.setPlayerAudioAttributes(attributes);
        }
    }

    @Override
    public void setLooping(boolean looping) {
        mStateContext.setPlayerLooping(looping);
    }

    @Override
    public void setVolume(float leftVolume, float rightVolume) {
        mStateContext.setPlayerVolume(leftVolume, rightVolume);
    }

    private void playSourceInit(String url) {
        PL.d(TAG, "playSourceInit");
        List<Command> commands = new ArrayList<>();
        commands.add(new Reset());
        commands.add(new SetDataSource(url));
        commands.add(new PrepareAsync());
        commands.add(new Start());
        commitCommands(commands);
    }

    private void onPlayerEnable() {
        mIsEnable = true;
        PL.d(TAG, "onPlayerEnable, mPendingCommands.size=%d", mPendingCommands.size());
        if (!mPendingCommands.isEmpty()) {
            PL.d(TAG, "onPlayerEnable, commit pending commands");
            commitCommands(new ArrayList<>(mPendingCommands));
            mPendingCommands.clear();
        }
    }

    private void commitCommands(List<Command> commands) {
        if (!mIsEnable) {
            mPendingCommands.addAll(commands);
            PL.d(TAG, "commitCommands, player is not enable, pendingCommands.size=%d", mPendingCommands.size());
            return;
        }
        mCommandInvoker.commit(commands);
    }
}
