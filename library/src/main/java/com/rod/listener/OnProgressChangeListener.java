package com.rod.listener;

/**
 * @author Rod
 * @date 2018/7/14
 */
public interface OnProgressChangeListener {

    void onProgressChange(int curPos, int bufferedPercent, int totalDuration);
}
