package com.example.astonfinalproject.main.core

import retrofit2.Retrofit


interface NetworkProvider {

    fun provideRetrofit(): Retrofit
}
