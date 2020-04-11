package com.rod.state;

import androidx.annotation.NonNull;

/**
 * @author Rod
 * @date 2020/3/28
 */
class StartedState extends BaseState {

    @Override
    public void start(PlayerProxy playerProxy) {
        playerProxy.callPlayerStart();
    }

    @Override
    public void seekTo(PlayerProxy playerProxy, int targetProgress) {
        playerProxy.callPlayerSeekTo(targetProgress);
    }

    @Override
    public void stop(PlayerProxy playerProxy) {
        playerProxy.callPlayerStop();
    }

    @Override
    public void pause(PlayerProxy playerProxy) {
        playerProxy.callPlayerPause();
    }

    @NonNull
    @Override
    public String toString() {
        return "StartedState";
    }
}
