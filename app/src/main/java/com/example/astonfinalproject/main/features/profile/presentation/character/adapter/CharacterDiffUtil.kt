package com.example.astonfinalproject.main.features.profile.presentation.character.adapter

import androidx.recyclerview.widget.DiffUtil

object CharacterDiffUtil: DiffUtil.ItemCallback<CharacterModel>() {
    override fun areItemsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CharacterModel, newItem: CharacterModel): Boolean {
        return oldItem == newItem
    }
}