package com.example.astonfinalproject.main.features.profile.data.api

import com.example.astonfinalproject.main.features.profile.data.dto.character.Character
import com.example.astonfinalproject.main.features.profile.data.dto.character.Result
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
    fun getResponseItemCharacter(charactersNames: String): Single<List<Result>> {
        return api.getItemCharacter(charactersNames)
    }
    fun getResponseItemEpisode(episodesNumbers: String): Single<List<Episode>> {
        return api.getItemEpisode(episodesNumbers)
    }
    fun getResponseItemLocation(locationsNames: String): Single<List<Location>> {
        return api.getItemLocation(locationsNames)
    }
}