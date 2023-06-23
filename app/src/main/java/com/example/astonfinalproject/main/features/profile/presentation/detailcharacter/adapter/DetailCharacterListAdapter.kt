package com.example.astonfinalproject.main.features.profile.presentation.detailcharacter.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.presentation.detailcharacter.DetailCharacterFragment
import com.example.astonfinalproject.main.features.profile.presentation.detaillocation.DetailLocationFragment
import com.example.astonfinalproject.main.features.profile.presentation.location.adapter.LocationListAdapter

class DetailCharacterListAdapter(
    private val detailCharacterFragment: DetailCharacterFragment
):
    androidx.recyclerview.widget.ListAdapter<DetailCharacterItemModel,  DetailCharacterListAdapter.DetailCharacterViewHolder>(
    DetailCharacterDiffUtil
){

    var list = emptyList<String>()

    class  DetailCharacterViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bind(model: DetailCharacterItemModel, detailCharacterFragment: DetailCharacterFragment){
            view.findViewById<TextView>(R.id.item_episode).text = model.episode

            view.findViewById<TextView>(R.id.item_episode).setOnClickListener {
                val bundle = Bundle()
                val episodeNumber: String = model.episode
                    .replace("https://rickandmortyapi.com/api/episode/", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                bundle.putString(LocationListAdapter.KEY_ARGS, episodeNumber)
                val detailLocationFragment = DetailLocationFragment()
                detailLocationFragment.arguments = bundle
                detailCharacterFragment.requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFragmentContainer, detailLocationFragment)
                    .addToBackStack("DetailCharacterFragment")
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):  DetailCharacterViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_detail_character, viewGroup, false)

        return  DetailCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder:  DetailCharacterViewHolder, position: Int) {
        val model = getItem(position)

        holder.bind(model, detailCharacterFragment)
    }

}
