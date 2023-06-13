package com.example.astonfinalproject.main.features.profile.presentation.location

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.presentation.location.adapter.LocationListAdapter
import com.example.astonfinalproject.main.features.profile.presentation.location.adapter.LocationModel

class LocationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.location_recView)
        val adapter = LocationListAdapter()
        recyclerView.adapter = adapter
        val list = List(100) {index ->
            LocationModel(
                id = index,
                name = "$index",
                type = "$index",
                dimension = "$index",
                residents = listOf("$index")
            )
        }
        val gridLayoutManager = GridLayoutManager(activity?.baseContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        adapter.submitList(list)
    }
}