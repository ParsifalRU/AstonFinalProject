package com.example.astonfinalproject.main.features.profile.data.api

import com.example.astonfinalproject.main.features.profile.data.dto.character.Character
import com.example.astonfinalproject.main.features.profile.data.dto.episode.Episode
import com.example.astonfinalproject.main.features.profile.data.dto.location.Location
import retrofit2.Call

class ApiRepository(
    private val api: Api
) {
    fun getResponseCharacter(): Call<Character> {
        return api.getCharacter()
    }
    fun getResponseEpisode(): Call<Episode> {
        return api.getEpisode()
    }
    fun getResponseLocation(): Call<Location> {
        return api.getLocation()
    }
}