package com.example.astonfinalproject.main.features.profile.presentation.detailepisode.adapter

data class DetailEpisodeModel(
    val id: Int,
    val name: String,
    val episode: String,
    val air_data: String,
    val characters: List<String>
)
