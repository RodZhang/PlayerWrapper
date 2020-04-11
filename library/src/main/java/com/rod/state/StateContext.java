package com.rod.state;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.rod.annotation.PlayerState;
import com.rod.command.CommandInvoker;
import com.rod.log.PL;

import java.io.IOException;

/**
 * @author Rod
 * @date 2018/7/18
 */
public class StateContext implements PlayerProxy {
    private static final String TAG = "StateContext";

    private MediaPlayer mMediaPlayer;
    private State mCurState = new IdleState();
    private CommandInvoker mCommandInvoker;
    private OnStateChangedListener mListener;

    public void attach(MediaPlayer player, CommandInvoker commandInvoker) {
        mCommandInvoker = commandInvoker;
        mMediaPlayer = player;
        mMediaPlayer.setOnPreparedListener(mp -> {
            PL.d(TAG, "onPrepared");
            changeState(new PreparedState());
            mCommandInvoker.invokeNextCommand();
        });
        mMediaPlayer.setOnSeekCompleteListener(mp -> {
            mCommandInvoker.invokeNextCommand();
        });
    }

    public void detach() {
        mMediaPlayer = null;
    }

    public void resetPlayer() {
        mCurState.reset(this);
    }

    public void setSource(String url) {
        mCurState.setDataSource(this, url);
    }

    public void prepareAsync() {
        mCurState.prepareAsync(this);
    }

    public void startPlayer() {
        mCurState.start(this);
    }

    public void pausePlayer() {
        mCurState.pause(this);
    }

    public void releasePlayer() {
        mCurState.release(this);
    }

    public void seekToPlayer(int targetProgress) {
        mCurState.seekTo(this, targetProgress);
    }

    @Override
    public void callPlayerReset() {
        PL.d(TAG, "callPlayerReset");
        mMediaPlayer.reset();
        changeState(new IdleState());
        mCommandInvoker.invokeNextCommand();
    }

    @Override
    public void callPlayerSetSource(String url) {
        PL.d(TAG, "callPlayerSetSource");
        try {
            mMediaPlayer.setDataSource(url);
            changeState(new InitializedState());
            mCommandInvoker.invokeNextCommand();
        } catch (IOException e) {
            PL.d(TAG, "callPlayerSetSource: error", e);
            changeState(new ErrorState());
            mCommandInvoker.clearCommand();
        }
    }

    @Override
    public void callPlayerPrepareAsync() {
        PL.d(TAG, "callPlayerPrepareAsync");
        mMediaPlayer.prepareAsync();
        changeState(new PreparingState());
    }

    @Override
    public void callPlayerStart() {
        PL.d(TAG, "callPlayerStart");
        mMediaPlayer.start();
        changeState(new StartedState());
        mCommandInvoker.invokeNextCommand();
    }

    @Override
    public void callPlayerPause() {
        PL.d(TAG, "callPlayerPause");
        mMediaPlayer.pause();
        changeState(new PausedState());
        mCommandInvoker.invokeNextCommand();
    }

    @Override
    public void callPlayerStop() {
        PL.d(TAG, "callPlayerStop");
        mMediaPlayer.stop();
        changeState(new StoppedState());
        mCommandInvoker.invokeNextCommand();
    }

    @Override
    public void callPlayerRelease() {
        PL.d(TAG, "callPlayerRelease");
        mMediaPlayer.release();
        changeState(new EndState());
        mCommandInvoker.invokeNextCommand();
    }

    @Override
    public void callPlayerSeekTo(int targetProgress) {
        PL.d(TAG, "callPlayerSeekTo, targetProgress=%d", targetProgress);
        mMediaPlayer.seekTo(targetProgress);
    }

    public void setOnStateChangedListener(OnStateChangedListener listener) {
        mListener = listener;
    }

    private void changeState(@NonNull State newState) {
        if (newState.getClass() == mCurState.getClass()) {
            PL.w(TAG, "changeState, same state, return, curState=%s", mCurState);
            return;
        }
        PL.d(TAG, "changeState: from [%s] to [%s]", mCurState, newState);
        mCurState = newState;
        if (mListener == null) {
            return;
        }

        int mappingState = mappingState(newState);
        if (mappingState == -1) {
            return;
        }
        mListener.onStateChanged(mappingState);
    }

    private int mappingState(State state) {
        if (state instanceof PausedState) {
            return PlayerState.PAUSED;
        } else if (state instanceof PreparingState) {
            return PlayerState.BUFFER_START;
        } else if (state instanceof PreparedState) {
            return PlayerState.BUFFER_END;
        } else if (state instanceof CompletedState) {
            return PlayerState.COMPLETE;
        } else {
            return -1;
        }
    }

    public void onComplete() {
        changeState(new CompletedState());
    }

    public int getCurrentPosition() {
        if (mCurState instanceof IdleState) {
            return 0;
        }
        return mMediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        if (mCurState instanceof IdleState) {
            return 0;
        }
        return mMediaPlayer.getDuration();
    }

    public int getVideoHeight() {
        if (mCurState instanceof IdleState) {
            return 0;
        }
        return mMediaPlayer.getVideoHeight();
    }

    public int getVideoWidth() {
        if (mCurState instanceof IdleState) {
            return 0;
        }
        return mMediaPlayer.getVideoWidth();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void setPlayerAudioAttributes(AudioAttributes attributes) {
        if (mCurState instanceof IdleState) {
            return;
        }

        mMediaPlayer.setAudioAttributes(attributes);
    }

    public void setPlayerLooping(boolean looping) {
        if (mCurState instanceof IdleState) {
            return;
        }

        mMediaPlayer.setLooping(looping);
    }

    public void setPlayerVolume(float leftVolume, float rightVolume) {
        if (mCurState instanceof IdleState) {
            return;
        }
        mMediaPlayer.setVolume(leftVolume, rightVolume);
    }

    public interface OnStateChangedListener {
        void onStateChanged(int newState);
    }
}
