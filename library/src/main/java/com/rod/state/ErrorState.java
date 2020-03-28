package com.rod.state;

import androidx.annotation.NonNull;

/**
 * @author Rod
 * @date 2020/3/28
 */
class ErrorState extends BaseState {

    @Override
    public void setSource(StateContext stateContext, String url) {
    }

    @Override
    public void prepareAsync(StateContext stateContext) {
    }

    @Override
    public void start(StateContext stateContext) {
    }

    @NonNull
    @Override
    public String toString() {
        return "ErrorState";
    }
}
