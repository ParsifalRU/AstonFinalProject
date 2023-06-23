package com.example.astonfinalproject.main.features.profile.presentation.location

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.data.api.ApiViewModel
import com.example.astonfinalproject.main.features.profile.presentation.location.adapter.LocationListAdapter
import com.example.astonfinalproject.main.features.profile.presentation.location.adapter.LocationModel

class LocationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LocationListAdapter
    private var list: List<LocationModel>? = null
    private var savedPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[ApiViewModel::class.java]

        recyclerView = view.findViewById(R.id.location_recView)
        val gridLayoutManager = GridLayoutManager(activity?.baseContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        adapter = LocationListAdapter(this@LocationFragment)
        recyclerView.adapter = adapter

        if (savedInstanceState != null) {
            savedPosition = savedInstanceState.getInt(POSITION, 0)
        }else{
            viewModel.getLocations()
        }

        viewModel.locationMutableLiveData.observe(viewLifecycleOwner) { response ->
            val tempList = mutableListOf<LocationModel>()

            response.results.forEach { location ->
                val characterPersonages: String = location.residents.toString()
                    .replace("https://rickandmortyapi.com/api/character/", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                Log.d("LOGTAG", characterPersonages)

                val itemCharacterLiveData = MutableLiveData<String>()

                itemCharacterLiveData.observe(viewLifecycleOwner) { characters ->

                    val locationModel = LocationModel(
                        id = location.id,
                        name = location.name,
                        type = location.type,
                        dimension = location.dimension,
                        residents = characters
                    )
                    tempList.add(locationModel)

                    Log.d("BALABOL", "$locationModel")

                    list = tempList
                    adapter.submitList(list)
                }
                viewModel.getItemCharacter(characterPersonages, itemCharacterLiveData)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(POSITION, (recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() ?: 0)
    }

    companion object {
        const val POSITION = "position"
    }
}