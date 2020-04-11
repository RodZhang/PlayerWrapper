package com.rod.state;

import androidx.annotation.NonNull;

/**
 * @author Rod
 * @date 2018/7/18
 */
public class IdleState extends BaseState {

    @Override
    public void reset(PlayerProxy playerProxy) {
        playerProxy.callPlayerReset();
    }

    @Override
    public void setDataSource(PlayerProxy playerProxy, String url) {
        playerProxy.callPlayerSetSource(url);
    }

    @Override
    public void release(PlayerProxy playerProxy) {
        playerProxy.callPlayerRelease();
    }

    @NonNull
    @Override
    public String toString() {
        return "IdleState";
    }
}
