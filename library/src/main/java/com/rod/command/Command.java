package com.rod.command;

import com.rod.state.StateContext;

/**
 * @author Rod
 * @date 2018/7/18
 */
public interface Command {

    boolean isInvokeImmediately();
    void execute(StateContext stateContext);
}
