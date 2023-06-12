package com.example.astonfinalproject.main.features.profile.presentation.characterScreen

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.astonfinalproject.R

class CharacterListAdapter:androidx.recyclerview.widget.ListAdapter<CharacterModel, CharacterListAdapter.CharacterViewHolder>(
    CharacterDiffUtil
){

    class CharacterViewHolder(private val view: View): ViewHolder(view){

        fun bind(model: CharacterModel){
            view.findViewById<TextView>(R.id.character_name_textView).text = model.name
            view.findViewById<TextView>(R.id.character_name_textView).text = model.status
            view.findViewById<TextView>(R.id.character_name_textView).text = model.species
            view.findViewById<TextView>(R.id.character_name_textView).text = model.gender
            setImage(view.context,"https://rickandmortyapi.com/api/character/avatar/1.jpeg", view.findViewById(R.id.character_imageView))
        }

        fun setImage(context: Context, url: String, imageView: ImageView){
            Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_character, viewGroup, false)
        return CharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)

    }


}