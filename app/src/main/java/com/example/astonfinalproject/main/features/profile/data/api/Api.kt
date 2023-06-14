package com.example.astonfinalproject.main.features.profile.data.api

import com.example.astonfinalproject.main.features.profile.data.dto.character.Character
import com.example.astonfinalproject.main.features.profile.data.dto.episode.Episode
import com.example.astonfinalproject.main.features.profile.data.dto.location.Location
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

private const val CHARACTER = "character"
private const val EPISODE = "episode"
private const val LOCATION = "location"

interface Api {
    @GET(CHARACTER)
    fun getCharacter(): Single<Character>

    @GET(EPISODE)
    fun getEpisode(): Single<Episode>

    @GET(LOCATION)
    fun getLocation(): Single<Location>
}