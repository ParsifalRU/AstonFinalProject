package com.example.astonfinalproject.main.features.profile.presentation.episode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.presentation.episode.adapter.EpisodeListAdapter
import com.example.astonfinalproject.main.features.profile.presentation.episode.adapter.EpisodeModel


class EpisodeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.episode_recView)
        val adapter = EpisodeListAdapter()
        recyclerView.adapter = adapter
        val list = List(100) {index ->
            EpisodeModel(
                id = index,
                name = "$index",
                episode = "$index",
                air_data = "$index",
                characters = listOf("$index")
            )
        }
        val gridLayoutManager = GridLayoutManager(activity?.baseContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        adapter.submitList(list)
    }
}