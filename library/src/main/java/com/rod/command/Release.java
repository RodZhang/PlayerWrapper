package com.rod.command;

import androidx.annotation.NonNull;

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

    @NonNull
    @Override
    public String toString() {
        return "Command[Release]";
    }
}
