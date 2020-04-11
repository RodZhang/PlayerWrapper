package com.rod.state;

/**
 * @author Rod
 * @date 2020/3/28
 */
class BaseState implements State {
    @Override
    public void reset(PlayerProxy playerProxy) {
    }

    @Override
    public void setDataSource(PlayerProxy playerProxy, String url) {
    }

    @Override
    public void prepareAsync(PlayerProxy playerProxy) {
    }

    @Override
    public void start(PlayerProxy playerProxy) {
    }

    @Override
    public void pause(PlayerProxy playerProxy) {
    }

    @Override
    public void stop(PlayerProxy playerProxy) {
    }

    @Override
    public void release(PlayerProxy playerProxy) {
    }

    @Override
    public void seekTo(PlayerProxy playerProxy, int targetProgress) {
    }
}
