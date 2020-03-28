package com.rod.mediaplayerwrapper;

import android.media.MediaPlayer;
import android.view.Surface;
import android.view.SurfaceHolder;

import com.rod.BasePlayer;
import com.rod.state.StateContext;

/**
 * @author Rod
 * @date 2018/7/14
 */
public class SafeMediaPlayer extends BasePlayer {

    private final MediaPlayer mMediaPlayer;
    private final StateContext mStateContext;

    public SafeMediaPlayer() {
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
    }
}
