package com.example.astonfinalproject.main.features.profile.data.api

import com.example.astonfinalproject.main.features.profile.data.dto.character.Character
import com.example.astonfinalproject.main.features.profile.data.dto.episode.Episode
import com.example.astonfinalproject.main.features.profile.data.dto.location.Location
import retrofit2.Call
import retrofit2.http.GET

private const val CHARACTER = "character"
private const val EPISODE = "episode"
private const val LOCATION = "location"

interface Api {
    @GET(CHARACTER)
    fun getCharacter():Call<Character>

    @GET(EPISODE)
    fun getEpisode():Call<Episode>

    @GET(LOCATION)
    fun getLocation():Call<Location>
}