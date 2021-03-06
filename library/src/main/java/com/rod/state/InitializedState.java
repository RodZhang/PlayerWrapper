package com.rod.state;

import androidx.annotation.NonNull;

/**
 * @author Rod
 * @date 2020/3/28
 */
class InitializedState extends BaseState {

    @Override
    public void prepareAsync(PlayerProxy playerProxy) {
        playerProxy.callPlayerPrepareAsync();
    }

    @NonNull
    @Override
    public String toString() {
        return "InitializedState";
    }
}
