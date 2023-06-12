package com.example.astonfinalproject.main.features.profile.data.api

import retrofit2.Call
import retrofit2.http.GET

private const val CHARACTER = "character"
private const val EPISODE = "./episode"
private const val LOCATION = "./location"

interface Api {
    @GET(CHARACTER)
    fun getCharacter():Call<com.example.astonfinalproject.main.features.profile.data.character.Character>

    @GET(EPISODE)
    fun getEpisode():com.example.astonfinalproject.main.features.profile.data.episode.Episode

    @GET(LOCATION)
    fun getLocation():com.example.astonfinalproject.main.features.profile.data.location.Location
}