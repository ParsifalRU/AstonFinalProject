package com.example.astonfinalproject.main.features.profile.data.api

import com.example.astonfinalproject.main.features.profile.data.dto.character.Character
import com.example.astonfinalproject.main.features.profile.data.dto.episode.Episode
import com.example.astonfinalproject.main.features.profile.data.dto.location.Location
import io.reactivex.rxjava3.core.Single

class ApiRepository(
    private val api: Api
) {
    fun getResponseCharacter(): Single<Character> {
        return api.getCharacter()
    }
    fun getResponseEpisode(): Single<Episode> {
        return api.getEpisode()
    }
    fun getResponseLocation(): Single<Location> {
        return api.getLocation()
    }
}