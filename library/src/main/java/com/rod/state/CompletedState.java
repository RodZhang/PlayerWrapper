package com.rod.state;

import androidx.annotation.NonNull;

/**
 * @author Rod
 * @date 2020/4/11
 */
public class CompletedState extends BaseState {

    @Override
    public void seekTo(PlayerProxy playerProxy, int targetProgress) {
        playerProxy.callPlayerSeekTo(targetProgress);
    }

    @Override
    public void start(PlayerProxy playerProxy) {
        playerProxy.callPlayerStart();
    }

    @Override
    public void stop(PlayerProxy playerProxy) {
        playerProxy.callPlayerStop();
    }

    @NonNull
    @Override
    public String toString() {
        return "CompletedState";
    }
}
