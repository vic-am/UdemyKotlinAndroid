package com.example.tasks.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasks.R
import java.util.*


class SplashActivity : AppCompatActivity() {

    private val timer = Timer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        timer.schedule(object : TimerTask() {
            override fun run() {
                jump()
            }
        }, 3000)

    }

    private fun jump() {
        timer.cancel()
        startActivity(
            Intent(
                this@SplashActivity,
                RegisterActivity::class.java
            )
        )
        finish()
    }

}