package com.rod.state;

import android.media.MediaPlayer;

/**
 * @author Rod
 * @date 2018/7/18
 */
public class StateContext {

    private MediaPlayer mMediaPlayer;
    private State mCurState;

    public void attach(MediaPlayer player) {
        mMediaPlayer = player;
    }

    public void detach() {
        mMediaPlayer = null;
    }



}
