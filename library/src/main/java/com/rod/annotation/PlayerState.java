package com.rod.annotation;

import androidx.annotation.IntDef;

/**
 * @author Rod
 * @date 2018/7/14
 */
@IntDef({PlayerState.IDLE,
        PlayerState.PREPAREING,
        PlayerState.PREPARED,
        PlayerState.BUFFERING,
        PlayerState.PLAYING,
        PlayerState.PAUSED,
        PlayerState.ERROR})
public @interface PlayerState {

    int IDLE = 0;
    int PREPAREING = 1;
    int PREPARED = 2;
    int BUFFERING = 3;
    int PLAYING = 4;
    int PAUSED = 5;
    int ERROR = 6;
}
