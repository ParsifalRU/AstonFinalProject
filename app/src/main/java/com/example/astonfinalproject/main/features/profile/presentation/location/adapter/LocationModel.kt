package com.example.astonfinalproject.main.features.profile.presentation.location.adapter

data class LocationModel(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>
)
