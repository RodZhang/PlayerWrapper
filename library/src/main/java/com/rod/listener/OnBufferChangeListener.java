package com.rod.listener;

/**
 * @author Rod
 * @date 2018/7/14
 */
public interface OnBufferChangeListener {

    void onBufferChange(int bufferedPercent, int totalDuration);
}
