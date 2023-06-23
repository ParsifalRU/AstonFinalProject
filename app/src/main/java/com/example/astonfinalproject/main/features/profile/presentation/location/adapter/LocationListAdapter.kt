package com.example.astonfinalproject.main.features.profile.presentation.location.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.presentation.detaillocation.DetailLocationFragment
import com.example.astonfinalproject.main.features.profile.presentation.location.LocationFragment


class LocationListAdapter(
    private val locationFragment: LocationFragment
) :androidx.recyclerview.widget.ListAdapter<LocationModel, LocationListAdapter.LocationViewHolder>(
    LocationDiffUtil
){
    class LocationViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        private val nameTextView: TextView = view.findViewById(R.id.location_name_textView)
        private val typeTextView: TextView = view.findViewById(R.id.location_type_textView)
        private val dimensionTextView: TextView =
            view.findViewById(R.id.location_dimension_textView)
        private val characterTextView: TextView =
            view.findViewById(R.id.location_characters_textView)

        fun bind(model: LocationModel, locationFragment: LocationFragment){
            nameTextView.text = model.name
            typeTextView.text = model.type
            dimensionTextView.text = model.dimension
            characterTextView.text = model.residents
            openDetailLocationFragment(model, locationFragment)
        }
        fun openDetailLocationFragment(model: LocationModel, locationFragment: LocationFragment){
            view.findViewById<LinearLayout>(R.id.location_linear_layout).setOnClickListener {
                val bundle = Bundle()
                bundle.putString(KEY_ARGS, "${model.id}")
                val detailLocationFragment = DetailLocationFragment()
                detailLocationFragment.arguments = bundle
                locationFragment.requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFragmentContainer, detailLocationFragment)
                    .addToBackStack("LocationFragment")
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_location, viewGroup, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model, locationFragment)
    }

    companion object{
        const val KEY_ARGS = "DetailLocationFragment"
    }
}