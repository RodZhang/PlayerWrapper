package com.rod.videohost;

import android.view.Surface;
import android.view.SurfaceHolder;

/**
 * @author Rod
 * @date 2018/7/18
 */
public interface VideoHostCallback {

    void setSurface(Surface surface);

    void setSurfaceHolder(SurfaceHolder surfaceHolder);
}
