package com.rod.playerwrapper

import android.app.Activity
import android.os.Handler
import android.view.ViewGroup
import com.rod.Player
import com.rod.mediaplayerwrapper.SafeMediaPlayer

/**
 *
 * @author Rod
 * @date 2018/7/15
 */
class SingleVideoActivity : Activity() {

    private lateinit var mVideoContainer: ViewGroup
    private val mUIHandler: Handler = Handler()

    private val mPlayer: Player = SafeMediaPlayer()

}