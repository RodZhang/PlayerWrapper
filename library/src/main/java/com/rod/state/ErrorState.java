package com.rod.state;

import androidx.annotation.NonNull;

/**
 * @author Rod
 * @date 2020/3/28
 */
class ErrorState extends BaseState {

    @Override
    public void prepareAsync(PlayerProxy stateContext) {
    }

    @Override
    public void start(PlayerProxy stateContext) {
    }

    @NonNull
    @Override
    public String toString() {
        return "ErrorState";
    }
}
