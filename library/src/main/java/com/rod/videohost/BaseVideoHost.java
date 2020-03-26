package com.rod.videohost;

import androidx.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.rod.Utils;

/**
 * @author Rod
 * @date 2018/7/15
 */
public abstract class BaseVideoHost implements VideoHost {

    @Override
    public void attachToContainer(@NonNull ViewGroup container) {
        View videoHost = getHost();
        if (videoHost.getParent() == container) {
            return;
        }
        Utils.removeFromParent(videoHost);
        container.addView(videoHost);

        ViewGroup.LayoutParams layoutParams = videoHost.getLayoutParams();
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    public void detachFromContainer() {
        Utils.removeFromParent(getHost());
    }

    @NonNull protected abstract View getHost();
}
