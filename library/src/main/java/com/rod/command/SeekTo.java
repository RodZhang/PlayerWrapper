package com.rod.command;

import com.rod.state.StateContext;

/**
 * @author Rod
 * @date 2020/4/6
 */
public class SeekTo implements Command {

    private final int mTargetProgress;

    public SeekTo(int targetProgress) {
        mTargetProgress = targetProgress;
    }

    @Override
    public boolean isInvokeImmediately() {
        return false;
    }

    @Override
    public void execute(StateContext stateContext) {
        stateContext.seekToPlayer(mTargetProgress);
    }
}
