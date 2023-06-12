package com.example.astonfinalproject.main.features.profile.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.data.api.Api
import com.example.astonfinalproject.main.features.profile.data.api.ApiRepository
import com.example.astonfinalproject.main.features.profile.data.character.Character
import com.example.astonfinalproject.main.network.NetworkModule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        setSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       supportFragmentManager
            .beginTransaction()
            .replace(R.id.mainFragmentContainer, CharacterFragment())
            .commit()

    }
    private fun setSplashScreen(){
        installSplashScreen()
        Thread.sleep(2000)

        thread {
            val takeRepo = ApiRepository(NetworkModule().provideRetrofit().create(Api::class.java))
            val response = takeRepo.getResponse()
                .enqueue(object : Callback<com.example.astonfinalproject.main.features.profile.data.character.Character>{
                    override fun onResponse(call: Call<Character>, response: Response<Character>) {
                        Log.d("LOGTAG", response.body().toString())
                    }

                    override fun onFailure(call: Call<Character>, t: Throwable) {
                        Log.d("LOGTAG", t.toString())
                    }

                })

        }

    }





}