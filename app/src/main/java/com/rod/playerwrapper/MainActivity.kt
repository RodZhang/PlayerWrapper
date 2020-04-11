package com.rod.playerwrapper

import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import com.rod.PlayerUserInterface
import com.rod.annotation.PlayerState
import com.rod.listener.OnProgressChangeListener
import com.rod.listener.OnStateChangeListener
import com.rod.log.PL
import com.rod.mediaplayerwrapper.SafeMediaPlayer
import com.rod.videohost.SurfaceViewHost
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.media_control_bar.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(), OnProgressChangeListener, OnStateChangeListener {
    companion object {
        const val TAG = "MainActivity"
    }

    private val player: PlayerUserInterface = SafeMediaPlayer()
    private var isSeeking = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player.addOnProgressChangeListener(this)
        player.addOnStateChangeListener(this)

        player.setVideoHost(SurfaceViewHost(this))
        play_btn.setOnClickListener {
            if (player.isPlaying()) {
                player.pause()
            } else {
                player.attachToContainer(video_container)
                player.play(videoList[2])
            }
        }
        seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                isSeeking = true
                player.onStartSeek()
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                isSeeking = false
                player.onEndSeek(seekBar?.progress ?: 0)
            }

        })
    }

    override fun onProgressChange(
            curPos: Int,
            bufferedPercent: Int,
            totalDuration: Int
    ) {
        if (isSeeking) {
            return
        }
        PL.d(TAG, "onProgressChange, curPos=$curPos, totalDuration=$totalDuration")
        duration.text = "${getDurationStr(curPos)}/${getDurationStr(totalDuration)}"
        seek_bar.max = totalDuration
        seek_bar.secondaryProgress = ((bufferedPercent / 100F) * totalDuration).toInt()
        seek_bar.progress = curPos
    }

    override fun onStateChanged(curState: Int) {
        PL.d(TAG, "onStateChanged, curState=$curState")
        when (curState) {
            PlayerState.BUFFER_START -> loading_view.visibility = View.VISIBLE
            PlayerState.BUFFER_END -> loading_view.visibility = View.GONE
            PlayerState.PLAYING -> play_btn.setImageResource(R.mipmap.ic_pause)
            PlayerState.PAUSED, PlayerState.COMPLETE -> play_btn.setImageResource(R.mipmap.ic_play)
            else -> PL.i(TAG, "onStateChanged, curState=%d", curState)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release();
        player.removeOnProgressChangeListener(this)
        player.removeOnStateChangeListener(this)
    }

    private fun getDurationStr(duration: Int): String {
        val totalMinutes: Int = duration / TimeUnit.MINUTES.toMillis(1).toInt()
        val hours: Int = totalMinutes / 60
        val showMinutes: Int = duration / TimeUnit.MINUTES.toMillis(1).toInt() % 60
        val seconds: Int = (duration - totalMinutes * TimeUnit.MINUTES.toMillis(1)).toInt() / 1000

        val result = StringBuilder()
        return result.append(fillSection(hours)).append(":")
                .append(fillSection(showMinutes)).append(":")
                .append(fillSection(seconds))
                .toString()
    }

    private fun fillSection(section: Int): String {
        return when {
            section == 0 -> {
                "00"
            }
            section < 10 -> {
                "0$section"
            }
            else -> {
                "$section"
            }
        }
    }
}
