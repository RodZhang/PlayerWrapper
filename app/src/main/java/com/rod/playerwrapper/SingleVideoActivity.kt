package com.rod.playerwrapper

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import org.jetbrains.anko.button
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.wrapContent

/**
 *
 * @author Rod
 * @date 2018/7/15
 */
class SingleVideoActivity : Activity() {

    private lateinit var mVideoContainer: ViewGroup
    private lateinit var mToggleBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mVideoContainer = frameLayout {

            mToggleBtn = button("toggle") {
                val lp = FrameLayout.LayoutParams(wrapContent, wrapContent)
                lp.gravity = Gravity.CENTER
                layoutParams = lp
            }
        }

        mVideoContainer.post {
            mVideoContainer.layoutParams.height = (MyApp.sScreenWidth.toFloat() / 16 * 9).toInt()
        }
    }
}