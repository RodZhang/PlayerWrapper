package com.rod.videohost;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.rod.log.PL;

/**
 * @author Rod
 * @date 2018/7/15
 */
public class SurfaceViewHost extends BaseVideoHost implements SurfaceHolder.Callback {
    private static final String TAG = "SurfaceViewHost";

    private final SurfaceView mSurfaceView;
    private VideoHostCallback mHostCallback;
    private SurfaceHolder mSurfaceHolder;

    public SurfaceViewHost(Context context) {
        mSurfaceView = new SurfaceView(context);
        mSurfaceView.getHolder().addCallback(this);
    }

    @NonNull
    @Override
    protected View getHost() {
        return mSurfaceView;
    }

    @Override
    public void attachPlayer(@NonNull VideoHostCallback hostCallback) {
        mHostCallback = hostCallback;
        if (mSurfaceHolder != null) {
            hostCallback.setSurfaceHolder(mSurfaceHolder);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        PL.d(TAG, "surfaceCreated");
        mSurfaceHolder = holder;
        if (mHostCallback != null) {
            mHostCallback.setSurfaceHolder(holder);
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        PL.d(TAG, "surfaceChanged, width=%d, height=%d", width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        PL.d(TAG, "surfaceDestroyed");
    }
}
