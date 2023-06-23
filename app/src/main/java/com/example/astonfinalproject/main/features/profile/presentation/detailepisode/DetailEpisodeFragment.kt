package com.example.astonfinalproject.main.features.profile.presentation.detailepisode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.data.api.ApiViewModel
import com.example.astonfinalproject.main.features.profile.presentation.detailepisode.adapter.DetailEpisodeItemModel
import com.example.astonfinalproject.main.features.profile.presentation.detailepisode.adapter.DetailEpisodeListAdapter


class DetailEpisodeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DetailEpisodeListAdapter
    private var list: List<DetailEpisodeItemModel>? = null
    private var savedPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.detail_episode_recView)
        val gridLayoutManager = GridLayoutManager(activity?.baseContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        adapter = DetailEpisodeListAdapter()
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this)[ApiViewModel::class.java]
        val itemLocationLiveData = MutableLiveData<String>()

        if (savedInstanceState != null) {
            savedPosition = savedInstanceState.getInt("position", 0)
        }else{
            val args = arguments?.getString(KEY_ARGS)
            viewModel.getItemEpisode("$args,-2"/*, itemCharacterLiveData = itemCharacterLiveData*/)
        }

        viewModel.itemEpisodeMutableLiveData.observe(viewLifecycleOwner) { response ->

            /*val listDetailLocationModel = List(response.size) {index ->
                 DetailLocationModel(
                     id = response[0].results[index].id,
                     name = response[0].results[index].name,
                     type = response[0].results[index].type,
                     dimension = response[0].results[index].dimension,
                     residents = response[0].results[index].residents,
                     url = response[0].results[index].url,
                     created = response[0].results[index].created,
                 )
             }*/
            list = List(response[0].characters.size) { index ->
                DetailEpisodeItemModel(
                    characters = response[0].characters[index]
                )
            }
            Log.d("LOGTAG", "$list")
            setData(response)
            adapter.submitList(list)
        }
    }

    private fun setData(model: List<com.example.astonfinalproject.main.features.profile.data.dto.episode.Result>){
        view?.findViewById<TextView>(R.id.detail_episode_name_textView)?.text = model[0].name
        view?.findViewById<TextView>(R.id.detail_episode_air_date_textView)?.text = model[0].air_date
        view?.findViewById<TextView>(R.id.detail_episode_episode_textView)?.text = model[0].episode
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(POSITION, (recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() ?: 0)
    }

    companion object {
        const val POSITION = "position"
        const val KEY_ARGS = "DetailEpisodeFragment"
    }
}