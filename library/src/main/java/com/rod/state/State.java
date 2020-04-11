package com.rod.state;

/**
 * @author Rod
 * @date 2018/7/17
 */
public interface State {
    void reset(PlayerProxy playerProxy);

    void setDataSource(PlayerProxy playerProxy, String url);

    void prepareAsync(PlayerProxy playerProxy);

    void start(PlayerProxy playerProxy);

    void pause(PlayerProxy playerProxy);

    void stop(PlayerProxy playerProxy);

    void release(PlayerProxy playerProxy);

    void seekTo(PlayerProxy playerProxy, int targetProgress);
}
