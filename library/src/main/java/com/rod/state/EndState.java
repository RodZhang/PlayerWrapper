package com.rod.state;

import androidx.annotation.NonNull;

/**
 * @author Rod
 * @date 2020/3/29
 */
class EndState extends BaseState {
    @Override
    public void resetPlayer(StateContext stateContext) {
    }

    @Override
    public void setSource(StateContext stateContext, String url) {
    }

    @Override
    public void prepareAsync(StateContext stateContext) {
    }

    @Override
    public void start(StateContext stateContext) {
    }

    @Override
    public void release(StateContext stateContext) {
    }

    @NonNull
    @Override
    public String toString() {
        return "EndState";
    }
}
