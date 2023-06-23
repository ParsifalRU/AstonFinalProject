package com.example.astonfinalproject.main.features.profile.presentation.detaillocation.adapter

import androidx.recyclerview.widget.DiffUtil

object DetailLocationDiffUtil : DiffUtil.ItemCallback<DetailLocationItemModel>() {

    override fun areItemsTheSame(
        oldItem: DetailLocationItemModel,
        newItem: DetailLocationItemModel
    ): Boolean {
        return oldItem.resident == newItem.resident
    }

    override fun areContentsTheSame(
        oldItem: DetailLocationItemModel,
        newItem: DetailLocationItemModel
    ): Boolean {
        return oldItem== newItem
    }
}