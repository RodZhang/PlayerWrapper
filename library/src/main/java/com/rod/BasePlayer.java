package com.rod;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.rod.listener.OnProgressChangeListener;
import com.rod.listener.OnStateChangeListener;
import com.rod.videohost.VideoHost;

/**
 * @author Rod
 * @date 2018/7/14
 */
public abstract class BasePlayer implements Player {

    private String mUrl;


    @Override
    public void attachToContainer(@NonNull ViewGroup container) {

    }

    @Override
    public void detachFromContainer() {

    }

    @Override
    public void setVideoHost(@NonNull VideoHost videoHost) {
        videoHost.attachPlayer(this);
    }

    @CallSuper
    @Override
    public void setSource(String url) {
        mUrl = url;
    }

    @Override
    public void addOnStateChangeListener(@NonNull OnStateChangeListener listener) {

    }

    @Override
    public void removeOnStateChangeListener(@NonNull OnStateChangeListener listener) {

    }

    @Override
    public void addOnProgressChangeListener(@NonNull OnProgressChangeListener listener) {

    }

    @Override
    public void removeOnProgressChangeListener(@NonNull OnProgressChangeListener listener) {

    }
}
