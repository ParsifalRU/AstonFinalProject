package com.example.astonfinalproject.main.features.profile.presentation.location.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R

class LocationListAdapter:androidx.recyclerview.widget.ListAdapter<LocationModel, LocationListAdapter.LocationViewHolder>(
    LocationDiffUtil
){

    class LocationViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bind(model: LocationModel){
            view.findViewById<TextView>(R.id.location_name_textView).text = model.name
            view.findViewById<TextView>(R.id.location_type_textView).text = model.type
            view.findViewById<TextView>(R.id.location_dimension_textView).text = model.dimension
            val listView = view.findViewById<ListView>(R.id.location_listView)
            val adapterListView: ArrayAdapter<String> =
                ArrayAdapter(view.context, android.R.layout.simple_list_item_1, model.residents)
            listView.adapter = adapterListView
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_location, viewGroup, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
    }
}