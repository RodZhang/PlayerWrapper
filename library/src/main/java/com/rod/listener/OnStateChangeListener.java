package com.rod.listener;

import com.rod.annotation.PlayerState;

/**
 * @author Rod
 * @date 2018/7/14
 */
public interface OnStateChangeListener {

    void onStateChanged(@PlayerState int curState);
}
