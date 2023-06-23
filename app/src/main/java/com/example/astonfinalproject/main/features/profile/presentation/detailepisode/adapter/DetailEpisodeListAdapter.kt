package com.example.astonfinalproject.main.features.profile.presentation.detailepisode.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R

class DetailEpisodeListAdapter:
    androidx.recyclerview.widget.ListAdapter<DetailEpisodeItemModel,  DetailEpisodeListAdapter.DetailEpisodeViewHolder>(
        DetailEpisodeDiffUtil
    ){

    var list = emptyList<String>()

    class  DetailEpisodeViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bind(model: DetailEpisodeItemModel){
            view.findViewById<TextView>(R.id.item_detail_episode).text = model.characters
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):  DetailEpisodeViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_detail_episode, viewGroup, false)

        return DetailEpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailEpisodeViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
    }
}