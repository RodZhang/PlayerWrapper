package com.rod.state;

import androidx.annotation.NonNull;

/**
 * @author Rod
 * @date 2020/3/28
 */
public class PreparedState extends BaseState {
    @Override
    public void setSource(StateContext stateContext, String url) {
    }

    @NonNull
    @Override
    public String toString() {
        return "PreparedState";
    }
}
