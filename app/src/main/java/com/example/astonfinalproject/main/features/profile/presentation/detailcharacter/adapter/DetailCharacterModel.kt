package com.example.astonfinalproject.main.features.profile.presentation.detailcharacter.adapter

data class DetailCharacterModel(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val origin_name: String,
    val origin_url: String,
    val location_name: String,
    val location_url: String,
    val episode:List<String>,
    val image: String,
)
