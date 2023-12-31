package com.example.astonfinalproject.main.features.profile.data.dto.location

data class Result(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String,
)
