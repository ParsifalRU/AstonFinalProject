package com.example.astonfinalproject.main.features.profile.presentation.detailcharacter.adapter


import androidx.recyclerview.widget.DiffUtil

object DetailCharacterDiffUtil : DiffUtil.ItemCallback<DetailCharacterItemModel>() {

    override fun areItemsTheSame(
        oldItem: DetailCharacterItemModel,
        newItem: DetailCharacterItemModel
    ): Boolean {
        return oldItem.episode == newItem.episode
    }

    override fun areContentsTheSame(
        oldItem: DetailCharacterItemModel,
        newItem: DetailCharacterItemModel
    ): Boolean {
        return oldItem== newItem
    }
}
