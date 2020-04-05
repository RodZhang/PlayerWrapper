package com.rod.state;

import android.media.MediaPlayer;

import androidx.annotation.NonNull;

import com.rod.command.CommandInvoker;
import com.rod.log.PL;

import java.io.IOException;

/**
 * @author Rod
 * @date 2018/7/18
 */
public class StateContext {
    private static final String TAG = "StateContext";

    private MediaPlayer mMediaPlayer;
    private State mCurState = new IdleState();
    private CommandInvoker mCommandInvoker;

    public void attach(MediaPlayer player, CommandInvoker commandInvoker) {
        mCommandInvoker = commandInvoker;
        mMediaPlayer = player;
        mMediaPlayer.setOnPreparedListener(mp -> {
            PL.d(TAG, "onPrepared");
            changeState(new PreparingState());
            mCommandInvoker.invokeNextCommand();
        });
    }

    public void detach() {
        mMediaPlayer = null;
    }

    public void resetPlayer() {
        if (mCurState == null) {
            return;
        }
        mCurState.resetPlayer(this);
    }

    public void setSource(String url) {
        if (mCurState == null) {
            return;
        }
        mCurState.setSource(this, url);
    }

    public void prepareAsync() {
        if (mCurState == null) {
            return;
        }
        mCurState.prepareAsync(this);
    }

    public void startPlayer() {
        if (mCurState == null) {
            return;
        }
        mCurState.start(this);
    }

    public void pausePlayer() {
        if (mCurState == null) {
            return;
        }
        mCurState.pause(this);
    }

    public void releasePlayer() {
        if (mCurState == null) {
            return;
        }
        mCurState.release(this);
    }

    public void callPlayerReset() {
        PL.d(TAG, "callPlayerReset");
        mMediaPlayer.reset();
        changeState(new IdleState());
        mCommandInvoker.invokeNextCommand();
    }

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

    public void callPlayerPrepareAsync() {
        PL.d(TAG, "callPlayerPrepareAsync");
        mMediaPlayer.prepareAsync();
        changeState(new PreparingState());
    }

    public void callPlayerStart() {
        PL.d(TAG, "callPlayerStart");
        mMediaPlayer.start();
        changeState(new StartedState());
        mCommandInvoker.invokeNextCommand();
    }

    public void callPlayerPause() {
        PL.d(TAG, "callPlayerPause");
        mMediaPlayer.pause();
        changeState(new PausedState());
        mCommandInvoker.invokeNextCommand();
    }

    public void callPlayerRelease() {
        PL.d(TAG, "callPlayerRelease");
        mMediaPlayer.release();
        changeState(new EndState());
        mCommandInvoker.invokeNextCommand();
    }

    private void changeState(@NonNull State newState) {
        if (newState.getClass() == mCurState.getClass()) {
            PL.w(TAG, "changeState, same state, return");
            return;
        }
        PL.d(TAG, "changeState: from [%s] to [%s]", mCurState, newState);
        mCurState = newState;
    }
}
