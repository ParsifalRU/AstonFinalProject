package com.example.astonfinalproject.main.features.profile.presentation.detaillocation.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.presentation.detailcharacter.DetailCharacterFragment
import com.example.astonfinalproject.main.features.profile.presentation.detaillocation.DetailLocationFragment

class DetailLocationListAdapter(
    private val detailLocationFragment: DetailLocationFragment
) :
    androidx.recyclerview.widget.ListAdapter<
            DetailLocationItemModel,
            DetailLocationListAdapter.DetailLocationViewHolder
            >(
        DetailLocationDiffUtil
    ){

    var list = emptyList<String>()

    class  DetailLocationViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bind(model: DetailLocationItemModel, detailLocationFragment: DetailLocationFragment){
            view.findViewById<TextView>(R.id.item_detail_location).text = model.resident
            openDetailCharacterFragment(model, detailLocationFragment)
        }
        private fun openDetailCharacterFragment(
            model: DetailLocationItemModel, detailLocationFragment: DetailLocationFragment
        ){
            view.findViewById<TextView>(R.id.item_detail_location).setOnClickListener {
                val bundle = Bundle()
                val episodeNumber: String = model.resident
                    .replace("https://rickandmortyapi.com/api/character/", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                bundle.putString("CharacterFragment", episodeNumber)
                val detailCharacterFragment = DetailCharacterFragment()
                detailCharacterFragment.arguments = bundle
                detailLocationFragment.requireActivity()
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.mainFragmentContainer, detailCharacterFragment)
                    .addToBackStack("DetailEpisodeFragment")
                    .commit()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):  DetailLocationViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_detail_location, viewGroup, false)

        return DetailLocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailLocationViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model, detailLocationFragment)
    }
}