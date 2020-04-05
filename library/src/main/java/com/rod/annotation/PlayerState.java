package com.rod.annotation;

import androidx.annotation.IntDef;

/**
 * @author Rod
 * @date 2018/7/14
 */
@IntDef({PlayerState.IDLE,
        PlayerState.PREPARING,
        PlayerState.PREPARED,
        PlayerState.BUFFER_START,
        PlayerState.BUFFER_END,
        PlayerState.PLAYING,
        PlayerState.PAUSED,
        PlayerState.ERROR,
        PlayerState.COMPLETE})
public @interface PlayerState {

    int IDLE = 0;
    int PREPARING = 1;
    int PREPARED = 2;
    int BUFFER_START = 3;
    int BUFFER_END = 4;
    int PLAYING = 5;
    int PAUSED = 6;
    int ERROR = 7;
    int COMPLETE = 8;
}
