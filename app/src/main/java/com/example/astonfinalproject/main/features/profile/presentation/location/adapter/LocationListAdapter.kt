package com.example.astonfinalproject.main.features.profile.presentation.location.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.data.api.ApiViewModel


class LocationListAdapter(
    private val viewModel: ApiViewModel
):androidx.recyclerview.widget.ListAdapter<LocationModel, LocationListAdapter.LocationViewHolder>(
    LocationDiffUtil
){
    class LocationViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val nameTextView: TextView = view.findViewById(R.id.location_name_textView)
        private val typeTextView: TextView = view.findViewById(R.id.location_type_textView)
        private val dimensionTextView: TextView = view.findViewById(R.id.location_dimension_textView)
        private val characterTextView: TextView = view.findViewById(R.id.location_characters_textView)

        fun bind(model: LocationModel){
            nameTextView.text = model.name
            typeTextView.text = model.type
            dimensionTextView.text = model.dimension
            characterTextView.text = model.residents
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LocationListAdapter.LocationViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_location, viewGroup, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
    }
}