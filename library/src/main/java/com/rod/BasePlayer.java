package com.rod;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.ViewGroup;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import com.rod.annotation.PlayerState;
import com.rod.listener.OnBufferChangeListener;
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
    private List<OnBufferChangeListener> mOnBufferChangeListeners = new ArrayList<>();
    private List<OnProgressChangeListener> mOnProgressChangeListeners = new ArrayList<>();
    private PlayerTimer mPlayerTimer = new PlayerTimer();

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
    public void addOnBufferChangeListener(@NonNull OnBufferChangeListener listener) {
        mOnBufferChangeListeners.add(listener);
    }

    @Override
    public void removeOnBufferChangeListener(@NonNull OnBufferChangeListener listener) {
        mOnBufferChangeListeners.remove(listener);
    }

    @Override
    public void addOnProgressChangedListener(@NonNull OnProgressChangeListener listener) {
        mOnProgressChangeListeners.add(listener);
    }

    @Override
    public void removeOnProgressChangedListener(@NonNull OnProgressChangeListener listener) {
        mOnProgressChangeListeners.remove(listener);
    }

    @Override
    public void setSurface(Surface surface) {

    }

    @Override
    public void setSurfaceHolder(SurfaceHolder surfaceHolder) {

    }

    protected void setTimerListener(PlayerTimer.OnTimeIntervalListener listener) {
        mPlayerTimer.setListener(listener);
    }

    protected void dispatchOnStateChanged(@PlayerState int newState) {
        updateTimerByPlayerState(newState);
        for (OnStateChangeListener listener : mStateListenerList) {
            listener.onStateChanged(newState);
        }
    }

    protected void dispatchOnBufferChanged(int bufferedPercent, int totalDuration) {
        for (OnBufferChangeListener listener : mOnBufferChangeListeners) {
            listener.onBufferChange(bufferedPercent, totalDuration);
        }
    }

    protected void dispatchProgressChanged(int curPos, int totalDuration) {
        for (OnProgressChangeListener listener : mOnProgressChangeListeners) {
            listener.onProgresschanged(curPos, totalDuration);
        }
    }

    private void updateTimerByPlayerState(@PlayerState int newState) {
        switch (newState) {
            case PlayerState.PLAYING:
                mPlayerTimer.start();
                break;
            case PlayerState.PAUSED:
            case PlayerState.BUFFER_START:
            case PlayerState.COMPLETE:
            case PlayerState.ERROR:
            case PlayerState.IDLE:
                mPlayerTimer.stop();
                break;
            default:
                break;
        }
    }
}
