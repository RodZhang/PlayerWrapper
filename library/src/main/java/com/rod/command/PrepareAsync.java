package com.rod.command;

import androidx.annotation.NonNull;

import com.rod.state.StateContext;

/**
 * @author Rod
 * @date 2018/7/18
 */
public class PrepareAsync implements Command {

    @Override
    public boolean isInvokeImmediately() {
        return false;
    }

    @Override
    public void execute(StateContext stateContext) {
        stateContext.prepareAsync();
    }

    @NonNull
    @Override
    public String toString() {
        return "Command[PrepareAsync]";
    }
}
