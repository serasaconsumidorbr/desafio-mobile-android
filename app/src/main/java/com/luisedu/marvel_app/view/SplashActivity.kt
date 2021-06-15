package com.luisedu.marvel_app.view

import android.app.Activity
import android.content.Intent
import android.media.MediaPlayer.OnCompletionListener
import android.net.Uri
import android.os.Bundle
import android.view.MotionEvent
import com.luisedu.marvel_app.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        try {
            val video =
                Uri.parse("android.resource://" + packageName + "/" + R.raw.marvel_intro)
                vvSplash.setVideoURI(video)
                vvSplash.setOnCompletionListener(OnCompletionListener { goToHomeActivity() })
            vvSplash.start()
        } catch (ex: Exception) {
            goToHomeActivity()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        goToHomeActivity()
        return true
    }

    private fun goToHomeActivity() {
        if (isFinishing) return
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}