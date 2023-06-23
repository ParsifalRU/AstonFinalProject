package com.example.astonfinalproject.main.features.profile.data.api

import com.example.astonfinalproject.main.features.profile.data.dto.character.Character
import com.example.astonfinalproject.main.features.profile.data.dto.episode.Episode
import com.example.astonfinalproject.main.features.profile.data.dto.location.Location
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path

private const val CHARACTER = "character"
private const val EPISODE = "episode"
private const val LOCATION = "location"
private const val ITEM_CHARACTER = "/{number_character}"
private const val ITEM_EPISODE = "/{number_episode}"
private const val ITEM_LOCATION = "/{number_location}"

interface Api {
    @GET(CHARACTER)
    fun getCharacter(): Single<Character>

    @GET(EPISODE)
    fun getEpisode(): Single<Episode>

    @GET(LOCATION)
    fun getLocation(): Single<Location>

    @GET(CHARACTER + ITEM_CHARACTER)
    fun getItemCharacter(
        @Path("number_character") number_character: String
    ): Single<List<com.example.astonfinalproject.main.features.profile.data.dto.character.Result>>

    @GET(EPISODE + ITEM_EPISODE)
    fun getItemEpisode(
        @Path("number_episode") number_episode: String
    ): Single<List<com.example.astonfinalproject.main.features.profile.data.dto.episode.Result>>

    @GET(LOCATION + ITEM_LOCATION)
    fun getItemLocation(
        @Path("number_location") number_location: String
    ): Single<List<com.example.astonfinalproject.main.features.profile.data.dto.location.Result>>
}