package com.example.astonfinalproject.main.features.profile.presentation.character

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.data.api.ApiViewModel
import com.example.astonfinalproject.main.features.profile.presentation.character.adapter.CharacterListAdapter
import com.example.astonfinalproject.main.features.profile.presentation.character.adapter.CharacterModel

class CharacterFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CharacterListAdapter
    private var list: List<CharacterModel>? = null
    private var savedPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.character_recView)
        val gridLayoutManager = GridLayoutManager(activity?.baseContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        adapter = CharacterListAdapter()
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this)[ApiViewModel::class.java]

        if (savedInstanceState != null) {
            savedPosition = savedInstanceState.getInt("position", 0)
        }

        viewModel.getCharacters()
        viewModel.characterMutableLiveData.observe(viewLifecycleOwner) { response ->
            list = List(response.results.size) { index ->
                CharacterModel(
                    id = response.results[index].id,
                    name = response.results[index].name,
                    gender = response.results[index].gender,
                    species = response.results[index].species,
                    status = response.results[index].status,
                    image = response.results[index].image,
                )
            }
            adapter.submitList(list) {
                recyclerView.scrollToPosition(savedPosition)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(POSITION, (recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() ?: 0)
    }

    companion object {
        const val POSITION = "position"
    }
}
