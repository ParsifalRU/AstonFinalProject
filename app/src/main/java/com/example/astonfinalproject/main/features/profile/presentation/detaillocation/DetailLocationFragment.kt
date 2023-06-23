package com.example.astonfinalproject.main.features.profile.presentation.detaillocation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.data.api.ApiViewModel
import com.example.astonfinalproject.main.features.profile.presentation.detaillocation.adapter.DetailLocationItemModel
import com.example.astonfinalproject.main.features.profile.presentation.detaillocation.adapter.DetailLocationListAdapter

class DetailLocationFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DetailLocationListAdapter
    private var list: List<DetailLocationItemModel>? = null
    private var savedPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deatil_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        recyclerView = view.findViewById(R.id.detail_location_recView)
        val gridLayoutManager = GridLayoutManager(
            activity?.baseContext, 2, GridLayoutManager.VERTICAL, false
        )
        recyclerView.layoutManager = gridLayoutManager
        adapter = DetailLocationListAdapter(this@DetailLocationFragment)
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this)[ApiViewModel::class.java]
        val itemLocationLiveData = MutableLiveData<String>()

        if (savedInstanceState != null) {
            savedPosition = savedInstanceState.getInt("position", 0)
        }else{
            val args = arguments?.getString(KEY_ARGS)
            viewModel.getItemLocation("$args,-2")
        }

        viewModel.itemLocationMutableLiveData.observe(viewLifecycleOwner) { response ->

            list = List(response[0].residents.size) { index ->
                DetailLocationItemModel(
                    id = response[0].id,
                    resident = response[0].residents[index]
                )
            }
            setData(response)

            adapter.submitList(list)
        }
    }

    private fun setData(
        model: List<com.example.astonfinalproject.main.features.profile.data.dto.location.Result>
    ){
        view?.findViewById<TextView>(R.id.detail_location_name_textView)?.text = model[0].name
        view?.findViewById<TextView>(R.id.detail_location_type_textView)?.text = model[0].type
        view?.findViewById<TextView>(R.id.detail_location_dimension_textView)?.text =
            model[0].dimension
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(
            POSITION,
            (recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() ?: 0
        )
    }

    companion object {
        const val POSITION = "position"
        const val KEY_ARGS = "DetailLocationFragment"
    }
}