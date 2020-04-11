package com.rod.state;

import androidx.annotation.NonNull;

/**
 * @author Rod
 * @date 2020/3/29
 */
class EndState extends BaseState {
    @Override
    public void reset(PlayerProxy playerProxy) {
    }

    @Override
    public void prepareAsync(PlayerProxy playerProxy) {
    }

    @Override
    public void start(PlayerProxy playerProxy) {
    }

    @Override
    public void release(PlayerProxy playerProxy) {
    }

    @NonNull
    @Override
    public String toString() {
        return "EndState";
    }
}
