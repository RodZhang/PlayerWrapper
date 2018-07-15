package com.rod.playerwrapper

import android.app.Application



/**
 *
 * @author Rod
 * @date 2018/7/15
 */
class MyApp : Application() {

    companion object {
        var sScreenWidth: Int = 0
        var sScreenHeight: Int = 0
    }

    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init() {
        val resources = this.resources
        val dm = resources.displayMetrics
        sScreenWidth = dm.widthPixels
        sScreenHeight = dm.heightPixels
    }
}