package com.rod;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.rod.annotation.PlayerState;
import com.rod.listener.OnProgressChangeListener;
import com.rod.listener.OnStateChangeListener;
import com.rod.log.PL;
import com.rod.videohost.VideoHost;
import com.rod.videohost.VideoHostCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rod
 * @date 2018/7/14
 */
public abstract class BasePlayer implements PlayerUserInterface, PlayerOperation, VideoHostCallback {
    private static final String TAG = "BasePlayer";

    protected String mUrl;
    private VideoHost mVideoHost;
    private List<OnStateChangeListener> mStateListenerList = new ArrayList<>();
    private List<OnProgressChangeListener> mProgressListenerList = new ArrayList<>();

    @Override
    public void attachToContainer(@NonNull ViewGroup container) {
        if (mVideoHost != null) {
            mVideoHost.attachToContainer(container);
        }
    }

    @Override
    public void detachFromContainer() {
        PL.d(TAG, "detachFromContainer");
        if (mVideoHost != null) {
            mVideoHost.detachFromContainer();
        }
    }

    @Override
    public void setVideoHost(@NonNull VideoHost videoHost) {
        mVideoHost = videoHost;
        mVideoHost.attachPlayer(this);
    }

    @CallSuper
    @Override
    public void setSource(String url) {
        mUrl = url;
    }

    @Override
    public void addOnStateChangeListener(@NonNull OnStateChangeListener listener) {
        mStateListenerList.add(listener);
    }

    @Override
    public void removeOnStateChangeListener(@NonNull OnStateChangeListener listener) {
        mStateListenerList.remove(listener);
    }

    @Override
    public void addOnProgressChangeListener(@NonNull OnProgressChangeListener listener) {
        mProgressListenerList.add(listener);
    }

    @Override
    public void removeOnProgressChangeListener(@NonNull OnProgressChangeListener listener) {
        mProgressListenerList.remove(listener);
    }

    @Override
    public void setSurface(Surface surface) {

    }

    @Override
    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {

    }

    protected void dispatchOnStateChanged(@PlayerState int newState) {
        for (OnStateChangeListener listener : mStateListenerList) {
            listener.onStateChanged(newState);
        }
    }

    protected void dispatchOnProgressChanged(int curPos, int totalPos) {
        for (OnProgressChangeListener listener : mProgressListenerList) {
            listener.onProgressChange(curPos, totalPos);
        }
    }
}
