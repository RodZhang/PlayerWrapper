package com.rod.command;

import androidx.annotation.NonNull;

import com.rod.state.StateContext;

/**
 * @author Rod
 * @date 2020/3/28
 */
public class SetDataSource implements Command {
    private String mUrl;

    public SetDataSource(String url) {
        mUrl = url;
    }

    @Override
    public boolean isInvokeImmediately() {
        return false;
    }

    @Override
    public void execute(StateContext stateContext) {
        stateContext.setSource(mUrl);
    }

    @NonNull
    @Override
    public String toString() {
        return "Command[SetDataSource{" +
                "mUrl='" + mUrl + '\'' +
                "}]";
    }
}
