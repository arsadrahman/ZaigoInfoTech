package com.app.zaigoinfotech.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.app.zaigoinfotech.R
import com.app.zaigoinfotech.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Hide ActionBar
        supportActionBar?.let { it -> it.hide() }

        viewModel.checkUserLoggedIn()
        viewModel.isUserLoggedIn.observe(this, Observer { isLoggedIn ->
            if (isLoggedIn)
                openActivityWithTimer(Intent(this, Dashboard::class.java))
            else
                openActivityWithTimer(Intent(this, LoginScreen::class.java))
        })

    }

    fun openActivityWithTimer(intent: Intent) {
        //Timer to open Activity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(intent)
            finish()
        }, 4000)
    }
}