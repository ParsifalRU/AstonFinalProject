package com.example.astonfinalproject.main.features.profile.presentation.episode.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.presentation.detailepisode.DetailEpisodeFragment
import com.example.astonfinalproject.main.features.profile.presentation.episode.EpisodeFragment

class EpisodeListAdapter(private val episodeFragment: EpisodeFragment) :androidx.recyclerview.widget.ListAdapter<EpisodeModel, EpisodeListAdapter.EpisodeViewHolder>(
    EpisodeDiffUtil
){

    class EpisodeViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        private val nameTextView: TextView = view.findViewById<TextView>(R.id.episode_name_textView)
        private val numberTextView: TextView = view.findViewById<TextView>(R.id.episode_number_textView)
        private val airDateTextView: TextView = view.findViewById<TextView>(R.id.episode_air_date_textView)
        private val characterTextView: TextView = view.findViewById(R.id.episode_characters_textView)

        fun bind(model: EpisodeModel, episodeFragment: EpisodeFragment){
            nameTextView.text = model.name
            numberTextView.text = model.episode
            airDateTextView.text = model.air_data
            airDateTextView.text = model.characters

            view.findViewById<LinearLayout>(R.id.item_episode_linearLayout).setOnClickListener {
                val bundle = Bundle()
                bundle.putString(KEY_ARGS, "${model.id}")
                val detailEpisodeFragment = DetailEpisodeFragment()
                detailEpisodeFragment.arguments = bundle
                episodeFragment.requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFragmentContainer, detailEpisodeFragment)
                    .addToBackStack("DetailEpisodeFragment")
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_episode, viewGroup, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model, episodeFragment)
    }
    companion object{
        const val KEY_ARGS = "DetailEpisodeFragment"
    }

}