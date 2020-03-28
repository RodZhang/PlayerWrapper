package com.rod.command;

import com.rod.state.StateContext;

/**
 * @author Rod
 * @date 2020/3/29
 */
public class Release implements Command {
    @Override
    public boolean isInvokeImmediately() {
        return true;
    }

    @Override
    public void execute(StateContext stateContext) {
        stateContext.releasePlayer();
    }
}
