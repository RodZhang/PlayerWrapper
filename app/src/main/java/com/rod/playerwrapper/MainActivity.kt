package com.rod.playerwrapper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rod.listener.OnProgressChangeListener
import com.rod.listener.OnStateChangeListener
import com.rod.log.PL
import com.rod.mediaplayerwrapper.SafeMediaPlayer
import com.rod.videohost.SurfaceViewHost
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnProgressChangeListener, OnStateChangeListener {
    companion object {
        const val TAG = "MainActivity"
    }

    private val player = SafeMediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player.addOnProgressChangeListener(this)
        player.addOnStateChangeListener(this)

        player.setVideoHost(SurfaceViewHost(this))
        btn_play.setOnClickListener {
            player.attachToContainer(video_container)
            player.play(videoList[1])
        }
        btn_pause.setOnClickListener {
            player.pause()
        }
    }

    override fun onProgressChange(curPos: Int, totalDuration: Int) {
        PL.d(TAG, "onProgressChange, curPos=$curPos, totalDuration=$totalDuration")
    }

    override fun onStateChanged(curState: Int) {
        PL.d(TAG, "onStateChanged, curState=$curState")
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release();
        player.removeOnProgressChangeListener(this)
        player.removeOnStateChangeListener(this)
    }
}
