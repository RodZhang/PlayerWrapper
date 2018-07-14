package com.rod.videohost;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.SurfaceView;
import android.view.View;

import com.rod.Player;

/**
 * @author Rod
 * @date 2018/7/15
 */
public class SurfaceViewHost extends BaseVideoHost {

    private final SurfaceView mSurfaceView;

    public SurfaceViewHost(Context context) {
        mSurfaceView = new SurfaceView(context);
    }

    @NonNull
    @Override
    protected View getHost() {
        return mSurfaceView;
    }

    @Override
    public void attachPlayer(@NonNull Player player) {

    }
}
