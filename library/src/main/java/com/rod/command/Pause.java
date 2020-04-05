package com.rod.command;

import androidx.annotation.NonNull;

import com.rod.state.StateContext;

/**
 * @author Rod
 * @date 2020/4/5
 */
public class Pause implements Command {
    @Override
    public boolean isInvokeImmediately() {
        return false;
    }

    @Override
    public void execute(StateContext stateContext) {
        stateContext.pausePlayer();
    }

    @NonNull
    @Override
    public String toString() {
        return "Command[Pause]";
    }
}
