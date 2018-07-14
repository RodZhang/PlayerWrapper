package com.rod.videohost;

import android.support.annotation.NonNull;
import android.view.ViewGroup;

import com.rod.Player;

/**
 * @author Rod
 * @date 2018/7/15
 */
public interface VideoHost {

    void attachToContainer(@NonNull ViewGroup container);

    void detachFromContainer();

    void attachPlayer(@NonNull Player player);
}
