package com.example.astonfinalproject.main.features.profile.presentation.episode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R

class EpisodeListAdapter:androidx.recyclerview.widget.ListAdapter<EpisodeModel, EpisodeListAdapter.EpisodeViewHolder>(
    EpisodeDiffUtil
){

    class EpisodeViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bind(model: EpisodeModel){
            view.findViewById<TextView>(R.id.episode_name_textView).text = model.name
            view.findViewById<TextView>(R.id.episode_number_textView).text = model.episode
            view.findViewById<TextView>(R.id.episode_air_date_textView).text = model.air_data
            val listView = view.findViewById<ListView>(R.id.episode_listView)
            val adapterListView: ArrayAdapter<String> =
                ArrayAdapter(view.context, android.R.layout.simple_list_item_1, model.characters)
            listView.adapter = adapterListView
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_episode, viewGroup, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
    }
}