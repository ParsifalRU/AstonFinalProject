package com.example.astonfinalproject.main.features.profile.presentation.detailepisode.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.presentation.detailcharacter.DetailCharacterFragment
import com.example.astonfinalproject.main.features.profile.presentation.detailepisode.DetailEpisodeFragment


class DetailEpisodeListAdapter(private val detailEpisodeFragment: DetailEpisodeFragment):
    androidx.recyclerview.widget.ListAdapter<
            DetailEpisodeItemModel,  DetailEpisodeListAdapter.DetailEpisodeViewHolder
            >(
        DetailEpisodeDiffUtil
    ){

    var list = emptyList<String>()

    class  DetailEpisodeViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bind(model: DetailEpisodeItemModel, detailEpisodeFragment: DetailEpisodeFragment){
            view.findViewById<TextView>(R.id.item_detail_episode).text = model.characters
            openDetailCharacterFragment(model, detailEpisodeFragment)
        }
        fun openDetailCharacterFragment(
            model: DetailEpisodeItemModel, detailEpisodeFragment: DetailEpisodeFragment
        ){
            view.findViewById<TextView>(R.id.item_detail_episode).setOnClickListener {
                val bundle = Bundle()
                val episodeNumber: String = model.characters
                    .replace("https://rickandmortyapi.com/api/character/", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                bundle.putString("CharacterFragment", episodeNumber)
                val detailCharacterFragment = DetailCharacterFragment()
                detailCharacterFragment.arguments = bundle
                detailEpisodeFragment.requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFragmentContainer, detailCharacterFragment)
                    .addToBackStack("DetailEpisodeFragment")
                    .commit()
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):  DetailEpisodeViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_detail_episode, viewGroup, false)
        return DetailEpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailEpisodeViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model, detailEpisodeFragment)
    }
}