package com.example.astonfinalproject.main.features.profile.presentation.detailepisode.adapter

import androidx.recyclerview.widget.DiffUtil

object DetailEpisodeDiffUtil: DiffUtil.ItemCallback<DetailEpisodeItemModel>() {

    override fun areItemsTheSame(
        oldItem: DetailEpisodeItemModel,
        newItem: DetailEpisodeItemModel
    ): Boolean {
        return oldItem.characters == newItem.characters
    }

    override fun areContentsTheSame(
        oldItem: DetailEpisodeItemModel,
        newItem: DetailEpisodeItemModel
    ): Boolean {
        return oldItem== newItem
    }
}