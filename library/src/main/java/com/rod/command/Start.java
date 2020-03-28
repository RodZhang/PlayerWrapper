package com.rod.command;

import androidx.annotation.NonNull;

import com.rod.state.StateContext;

/**
 * @author Rod
 * @date 2020/3/28
 */
public class Start implements Command {
    @Override
    public boolean isInvokeImmediately() {
        return false;
    }

    @Override
    public void execute(StateContext stateContext) {
        stateContext.startPlayer();
    }

    @NonNull
    @Override
    public String toString() {
        return "Command[Start]";
    }
}
