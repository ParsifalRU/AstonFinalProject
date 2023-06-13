package com.example.astonfinalproject.main.features.profile.presentation.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.presentation.character.adapter.CharacterListAdapter
import com.example.astonfinalproject.main.features.profile.presentation.character.adapter.CharacterModel

class CharacterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view.findViewById<RecyclerView>(R.id.character_recView)
        val adapter = CharacterListAdapter()
        recyclerView.adapter = adapter
        val list = List(100) {index ->
            CharacterModel(
                id = index,
                name = "$index",
                gender = "$index",
                species = "$index",
                status = "$index",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            )
        }
        val gridLayoutManager = GridLayoutManager(activity?.baseContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        adapter.submitList(list)
    }
}