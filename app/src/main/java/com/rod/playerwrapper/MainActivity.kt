package com.rod.playerwrapper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rod.mediaplayerwrapper.SafeMediaPlayer
import com.rod.videohost.SurfaceViewHost
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val player = SafeMediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player.setVideoHost(SurfaceViewHost(this))
        btn_play.setOnClickListener {
            player.attachToContainer(video_container)
            player.play(videoList[1])
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release();
    }
}
