package com.rod.state;

import androidx.annotation.NonNull;

/**
 * @author Rod
 * @date 2020/4/11
 */
class StoppedState extends BaseState {

    @Override
    public void prepareAsync(PlayerProxy playerProxy) {
        playerProxy.callPlayerPrepareAsync();
    }

    @Override
    public void stop(PlayerProxy playerProxy) {
        playerProxy.callPlayerStop();
    }

    @NonNull
    @Override
    public String toString() {
        return "StoppedState";
    }
}
