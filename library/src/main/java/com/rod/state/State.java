package com.rod.state;

/**
 * @author Rod
 * @date 2018/7/17
 */
public interface State {
    void resetPlayer(StateContext stateContext);

    void setSource(StateContext stateContext, String url);

    void prepareAsync(StateContext stateContext);

    void start(StateContext stateContext);

    void pause(StateContext stateContext);

    void release(StateContext stateContext);

    void seekTo(StateContext stateContext, int targetProgress);
}
