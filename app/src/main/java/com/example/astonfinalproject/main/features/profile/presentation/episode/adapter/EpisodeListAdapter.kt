package com.example.astonfinalproject.main.features.profile.presentation.episode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R

class EpisodeListAdapter:androidx.recyclerview.widget.ListAdapter<EpisodeModel, EpisodeListAdapter.EpisodeViewHolder>(
    EpisodeDiffUtil
){

    class EpisodeViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        private val nameTextView: TextView = view.findViewById<TextView>(R.id.episode_name_textView)
        private val numberTextView: TextView = view.findViewById<TextView>(R.id.episode_number_textView)
        private val airDateTextView: TextView = view.findViewById<TextView>(R.id.episode_air_date_textView)
        private val characterTextView: TextView = view.findViewById(R.id.episode_characters_textView)

        fun bind(model: EpisodeModel){
            nameTextView.text = model.name
            numberTextView.text = model.episode
            airDateTextView.text = model.air_data
            airDateTextView.text = model.characters
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