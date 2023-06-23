package com.example.astonfinalproject.main.features.profile.presentation.location.adapter

import androidx.recyclerview.widget.DiffUtil

object LocationDiffUtil: DiffUtil.ItemCallback<LocationModel>() {
    override fun areItemsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: LocationModel, newItem: LocationModel): Boolean {
        return oldItem == newItem
    }


}