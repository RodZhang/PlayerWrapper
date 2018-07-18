package com.rod.videohost;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.TextureView;
import android.view.View;

/**
 * @author Rod
 * @date 2018/7/15
 */
public class TextureViewHost extends BaseVideoHost {

    private final TextureView mTextureView;

    public TextureViewHost(Context context) {
        mTextureView = new TextureView(context);
    }

    @NonNull
    @Override
    protected View getHost() {
        return mTextureView;
    }

    @Override
    public void attachPlayer(@NonNull VideoHostCallback hostCallback) {

    }
}
