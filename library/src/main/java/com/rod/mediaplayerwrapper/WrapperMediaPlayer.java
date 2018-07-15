package com.rod.mediaplayerwrapper;

import android.media.MediaPlayer;

import com.rod.BasePlayer;

/**
 * @author Rod
 * @date 2018/7/14
 */
public class WrapperMediaPlayer extends BasePlayer {

    private final MediaPlayer mMediaPlayer;

    public WrapperMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
    }

    @Override
    public void setSource(String url) {
        super.setSource(url);
//        mMediaPlayer.setDataSource(url);
    }

    @Override
    public void prepare() {
//        mMediaPlayer.prepare();
    }

    @Override
    public void stop() {
        mMediaPlayer.stop();
    }

    @Override
    public void release() {
        mMediaPlayer.release();
    }

    @Override
    public void play() {
        mMediaPlayer.start();
    }

    @Override
    public void pause() {
        mMediaPlayer.pause();
    }

    @Override
    public void seekTo(int pos) {
        mMediaPlayer.seekTo(pos);
    }
}
