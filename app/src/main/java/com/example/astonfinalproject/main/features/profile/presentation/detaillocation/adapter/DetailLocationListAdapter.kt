package com.example.astonfinalproject.main.features.profile.presentation.detaillocation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R

class DetailLocationListAdapter :
    androidx.recyclerview.widget.ListAdapter<DetailLocationItemModel,  DetailLocationListAdapter.DetailLocationViewHolder>(
        DetailLocationDiffUtil
    ){

    var list = emptyList<String>()

    class  DetailLocationViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bind(model: DetailLocationItemModel){
            view.findViewById<TextView>(R.id.item_detail_location).text = model.resident
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):  DetailLocationViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_detail_location, viewGroup, false)

        return DetailLocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailLocationViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)
    }

}