package com.rod.mediaplayerwrapper;

import android.media.MediaPlayer;
import androidx.annotation.NonNull;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.rod.BasePlayer;
import com.rod.state.StateContext;

import java.io.IOException;

/**
 * @author Rod
 * @date 2018/7/14
 */
public class WrapperMediaPlayer extends BasePlayer {

    private final MediaPlayer mMediaPlayer;
    private final StateContext mStateContext;

    public WrapperMediaPlayer() {
        mMediaPlayer = new MediaPlayer();
        mStateContext = new StateContext();
        mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                return false;
            }
        });
    }

    @Override
    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {
        mMediaPlayer.setDisplay(surfaceHolder);
    }

    @Override
    public void setSurface(Surface surface) {
        mMediaPlayer.setSurface(surface);
    }

    @Override
    public void onSurfaceDestroyed() {
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
    public void playWithSource(@NonNull String url) {
        try {
            mMediaPlayer.setDataSource(url);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mMediaPlayer.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

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
