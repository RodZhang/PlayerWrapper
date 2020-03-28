package com.rod.command;

import androidx.annotation.NonNull;

import com.rod.state.StateContext;

/**
 * @author Rod
 * @date 2020/3/28
 */
public class Reset implements Command {
    @Override
    public boolean isInvokeImmediately() {
        return true;
    }

    @Override
    public void execute(StateContext stateContext) {
        stateContext.resetPlayer();
    }

    @NonNull
    @Override
    public String toString() {
        return "Command[Reset]";
    }
}
