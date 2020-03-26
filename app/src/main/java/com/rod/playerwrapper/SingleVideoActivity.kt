package com.rod.playerwrapper

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.view.SurfaceView
import android.view.TextureView
import android.view.ViewGroup
import com.rod.Player
import com.rod.mediaplayerwrapper.WrapperMediaPlayer

/**
 *
 * @author Rod
 * @date 2018/7/15
 */
class SingleVideoActivity : Activity() {

    private lateinit var mVideoContainer: ViewGroup
    private val mUIHandler: Handler = Handler()

    private val mPlayer: Player = WrapperMediaPlayer()

    private val mSurfaceView: SurfaceView
        get() {
            val surfaceView = SurfaceView(this@SingleVideoActivity)
            return surfaceView
        }
    private lateinit var mTextureView: TextureView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun updateContainer(viewGroup: ViewGroup?, remove: ViewGroup?) {
        if (remove != null) {
            remove.removeAllViews()
        }
        if (viewGroup != null) {
            viewGroup.addView(mTextureView)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}