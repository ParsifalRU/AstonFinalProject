package com.example.astonfinalproject.main.features.profile.data.api

import com.example.astonfinalproject.main.features.profile.data.character.Character
import retrofit2.Call

class ApiRepository(
    val api: Api
) {
    fun getResponse(): Call<Character> {
        return api.getCharacter()
    }
}