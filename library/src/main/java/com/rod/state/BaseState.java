package com.rod.state;

/**
 * @author Rod
 * @date 2020/3/28
 */
class BaseState implements State {
    @Override
    public void resetPlayer(StateContext stateContext) {
        stateContext.callPlayerReset();
    }

    @Override
    public void setSource(StateContext stateContext, String url) {
        stateContext.callPlayerSetSource(url);
    }

    @Override
    public void prepareAsync(StateContext stateContext) {
        stateContext.callPlayerPrepareAsync();
    }

    @Override
    public void start(StateContext stateContext) {
        stateContext.callPlayerStart();
    }

    @Override
    public void pause(StateContext stateContext) {
        stateContext.callPlayerPause();
    }

    @Override
    public void release(StateContext stateContext) {
        stateContext.callPlayerRelease();
    }

    @Override
    public void seekTo(StateContext stateContext, int targetProgress) {
        stateContext.callPlayerSeekTo(targetProgress);
    }
}
