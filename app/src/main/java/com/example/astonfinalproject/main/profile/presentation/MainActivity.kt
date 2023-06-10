package com.example.astonfinalproject.main.profile.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.profile.presentation.mainscreen.menu.CharacterFragment


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        setSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.mainFragmentContainer, CharacterFragment()).commit()

    }
    private fun setSplashScreen(){
        installSplashScreen()
        Thread.sleep(2000)
    }
}