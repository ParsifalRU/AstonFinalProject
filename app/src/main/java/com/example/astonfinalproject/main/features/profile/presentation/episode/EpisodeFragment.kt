package com.example.astonfinalproject.main.features.profile.presentation.episode

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.data.api.ApiViewModel
import com.example.astonfinalproject.main.features.profile.presentation.character.CharacterFragment
import com.example.astonfinalproject.main.features.profile.presentation.episode.adapter.EpisodeListAdapter
import com.example.astonfinalproject.main.features.profile.presentation.episode.adapter.EpisodeModel


class EpisodeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: EpisodeListAdapter
    private var list: List<EpisodeModel>? = null
    private var savedPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_episode, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.episode_recView)
        val gridLayoutManager = GridLayoutManager(activity?.baseContext, 2, GridLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = gridLayoutManager
        adapter = EpisodeListAdapter(this@EpisodeFragment)
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this)[ApiViewModel::class.java]

        if (savedInstanceState != null) {
            savedPosition = savedInstanceState.getInt(POSITION, 0)
        }else{
            viewModel.getEpisodes()
        }

        viewModel.episodeMutableLiveData.observe(viewLifecycleOwner) { response ->
            val tempList = mutableListOf<EpisodeModel>()

            response.results.forEach { episode ->
                val characterPersonages: String = episode.characters.toString()
                    .replace("https://rickandmortyapi.com/api/character/", "")
                    .replace("[", "")
                    .replace("]", "")
                    .replace(" ", "")
                Log.d("LOGTAG", characterPersonages)

                val itemCharacterLiveData = MutableLiveData<String>()

                itemCharacterLiveData.observe(viewLifecycleOwner) { characters ->

                    val episodeModel = EpisodeModel(
                        id = episode.id,
                        name = episode.name,
                        episode = episode.episode,
                        air_data = episode.air_date,
                        characters = characters
                    )
                    tempList.add(episodeModel)

                    Log.d("BALABOL", "$episodeModel")

                    list = tempList
                    adapter.submitList(list)
                }
                viewModel.getItemCharacter(characterPersonages, itemCharacterLiveData)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CharacterFragment.POSITION, (recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() ?: 0)
    }

    companion object {
        const val POSITION = "position"
    }
}