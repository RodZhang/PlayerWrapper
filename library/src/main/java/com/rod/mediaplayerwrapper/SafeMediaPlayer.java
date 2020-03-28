package com.rod.mediaplayerwrapper;

import android.media.MediaPlayer;
import android.text.TextUtils;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.rod.BasePlayer;
import com.rod.command.Command;
import com.rod.command.CommandInvoker;
import com.rod.command.PrepareAsync;
import com.rod.command.Release;
import com.rod.command.Reset;
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
    private boolean mIsEnable = false;

    public SafeMediaPlayer() {
        StateContext stateContext = new StateContext();
        mCommandInvoker.attach(stateContext);
        stateContext.attach(mMediaPlayer, mCommandInvoker);
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                PL.d(TAG, "what=%d, extra=%d", what, extra);
                return false;
            }
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
    public void release() {
        PL.d(TAG, "release");
        detachFromContainer();
        List<Command> commands = new ArrayList<>();
        commands.add(new Release());
        commitCommands(commands);
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
