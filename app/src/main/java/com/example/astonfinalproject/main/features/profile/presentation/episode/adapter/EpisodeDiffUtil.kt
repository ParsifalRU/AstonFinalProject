package com.example.astonfinalproject.main.features.profile.presentation.episode.adapter

import androidx.recyclerview.widget.DiffUtil

object EpisodeDiffUtil: DiffUtil.ItemCallback<EpisodeModel>() {
    override fun areItemsTheSame(oldItem: EpisodeModel, newItem: EpisodeModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: EpisodeModel, newItem: EpisodeModel): Boolean {
        return oldItem == newItem
    }
}