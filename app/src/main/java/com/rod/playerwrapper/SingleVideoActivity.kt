package com.rod.playerwrapper

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import com.rod.Player
import com.rod.mediaplayerwrapper.WrapperMediaPlayer
import com.rod.videohost.SurfaceViewHost
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.button
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.wrapContent

/**
 *
 * @author Rod
 * @date 2018/7/15
 */
class SingleVideoActivity : Activity() {

    private lateinit var mVideoContainer: ViewGroup
    private lateinit var mToggleBtn: Button

    private val mPlayer: Player = WrapperMediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mPlayer.setVideoHost(SurfaceViewHost(this))

        mVideoContainer = frameLayout {
            backgroundColor = Color.parseColor("#FF0000")

            mToggleBtn = button("toggle") {
                val lp = FrameLayout.LayoutParams(wrapContent, wrapContent)
                lp.gravity = Gravity.CENTER
                layoutParams = lp
                onClick {
                    mPlayer.playWithSource("http://huya-w10.huya.com/1729/12409280/1000/387e9e4157b8da77a44258b0c21d6075.m3u8")
                    visibility = View.GONE
                }
            }
        }

        mVideoContainer.post {
            mVideoContainer.layoutParams.height = (MyApp.sScreenWidth.toFloat() / 16 * 9).toInt()
            mPlayer.attachToContainer(mVideoContainer)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mPlayer.release()
    }
}