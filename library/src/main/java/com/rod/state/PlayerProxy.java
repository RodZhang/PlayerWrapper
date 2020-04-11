package com.rod.state;

/**
 * @author Rod
 * @date 2020/4/11
 */
interface PlayerProxy {
    void callPlayerReset();

    void callPlayerSetSource(String url);

    void callPlayerPrepareAsync();

    void callPlayerStart();

    void callPlayerPause();

    void callPlayerStop();

    void callPlayerRelease();

    void callPlayerSeekTo(int targetProgress);
}
