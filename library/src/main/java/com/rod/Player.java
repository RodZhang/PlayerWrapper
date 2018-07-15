package com.rod;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.rod.listener.OnProgressChangeListener;
import com.rod.listener.OnStateChangeListener;
import com.rod.videohost.VideoHost;

/**
 * @author Rod
 * @date 2018/7/14
 */
public interface Player extends MinPlayer {

    void attachToContainer(@NonNull ViewGroup container);

    void detachFromContainer();

    void setVideoHost(@NonNull VideoHost videoHost);

    void setSource(String url);

    void prepare();

    void stop();

    void release();

    void addOnStateChangeListener(@NonNull OnStateChangeListener listener);

    void removeOnStateChangeListener(@NonNull OnStateChangeListener listener);

    void addOnProgressChangeListener(@NonNull OnProgressChangeListener listener);

    void removeOnProgressChangeListener(@NonNull OnProgressChangeListener listener);
}