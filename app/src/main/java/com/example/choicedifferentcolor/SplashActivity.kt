package com.example.choicedifferentcolor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.airbnb.lottie.LottieAnimationView
import com.example.choicedifferentcolor.databinding.ActivitySplashBinding
import kotlinx.coroutines.*


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.loadingImage.playAnimation()


        CoroutineScope(Dispatchers.Main).launch {

            delay(2000L)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)

            startActivity(intent)
            finish()


        }

    }
}