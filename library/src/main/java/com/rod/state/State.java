package com.rod.state;

/**
 * @author Rod
 * @date 2018/7/17
 */
public interface State {

    void start(String url);

    void resume();

    void seekTo();

    void pause();

    void stop();

    void release();
}
