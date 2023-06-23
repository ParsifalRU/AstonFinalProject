package com.example.astonfinalproject.main.features.profile.presentation.detailcharacter

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.astonfinalproject.R
import com.example.astonfinalproject.main.features.profile.data.api.ApiViewModel
import com.example.astonfinalproject.main.features.profile.data.dto.character.Result
import com.example.astonfinalproject.main.features.profile.presentation.detailcharacter.adapter.DetailCharacterItemModel
import com.example.astonfinalproject.main.features.profile.presentation.detailcharacter.adapter.DetailCharacterListAdapter
import com.example.astonfinalproject.main.features.profile.presentation.detaillocation.DetailLocationFragment

class DetailCharacterFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: DetailCharacterListAdapter
    private var list: List<DetailCharacterItemModel>? = null
    private var savedPosition: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail_character, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBackArrow()
        recyclerView = view.findViewById(R.id.detail_character_recView)
        adapter = DetailCharacterListAdapter(this@DetailCharacterFragment)
        recyclerView.adapter = adapter

        val viewModel = ViewModelProvider(this)[ApiViewModel::class.java]
        val itemCharacterLiveData = MutableLiveData<String>()

        if (savedInstanceState != null) {
            savedPosition = savedInstanceState.getInt(POSITION, 0)
        }else{
            val args = arguments?.getString(KEY_ARGS)
            try{
                viewModel.getItemCharacter("$args,-2", itemCharacterLiveData)
            }catch (e:Exception){
                Toast.makeText(requireContext(), "Ошибка загрузки", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.itemCharacterMutableLiveData.observe(viewLifecycleOwner) { response ->

            list = List(response[0].episode.size) { index ->
                DetailCharacterItemModel(
                    id = response[0].id,
                    episode = response[0].episode[index]
                )
            }
            setData(response)
            adapter.submitList(list)
        }
    }

    private fun setData(model: List<Result>){
        view?.findViewById<TextView>(R.id.detail_character_name_textView)?.text = model[0].name
        view?.findViewById<TextView>(R.id.detail_character_status_textView)?.text = model[0].status
        view?.findViewById<TextView>(R.id.detail_character_species_textView)?.text =
            model[0].species
        view?.findViewById<TextView>(R.id.detail_character_gender_textView)?.text = model[0].gender
        view?.findViewById<TextView>(R.id.detail_character_origin_textView)?.text =
            model[0].origin.name
        view?.findViewById<TextView>(R.id.detail_character_location_textView)?.text =
            model[0].location.name
        try {
            setImage(
                requireContext(),
                model[0].image,
                view?.findViewById(R.id.detail_character_imageView)
            )
        }catch (exception : Exception){
            Log.d("LOGTAG", exception.toString())
        }

        openDetailLocationOriginFragment(model)

        openDetailLocationFragment(model)
    }

    private fun openDetailLocationOriginFragment(model: List<Result>){
        view?.findViewById<TextView>(R.id.detail_character_origin_textView)?.setOnClickListener {
            val bundle = Bundle()
            val locationNumber: String = model[0].origin.url
                .replace("https://rickandmortyapi.com/api/location/", "")
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
            bundle.putString("LocationFragment", locationNumber)
            val detailLocationFragment = DetailLocationFragment()
            detailLocationFragment.arguments = bundle
            this.requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragmentContainer, detailLocationFragment)
                .addToBackStack("DetailCharacterFragment")
                .commit()
        }
    }

    private fun openDetailLocationFragment(model: List<Result>){
        view?.findViewById<TextView>(R.id.detail_character_location_textView)?.setOnClickListener {
            val bundle = Bundle()
            val locationNumber: String = model[0].location.url
                .replace("https://rickandmortyapi.com/api/location/", "")
                .replace("[", "")
                .replace("]", "")
                .replace(" ", "")
            bundle.putString("LocationFragment", locationNumber)
            val detailLocationFragment = DetailLocationFragment()
            detailLocationFragment.arguments = bundle
            this.requireActivity()
                .supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainFragmentContainer, detailLocationFragment)
                .addToBackStack("DetailCharacterFragment")
                .commit()
        }
    }

    private fun setImage(context: Context, url: String, imageView: ImageView?){
        Glide
            .with(context)
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .into(imageView!!)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(
            POSITION,
            (recyclerView.layoutManager as? GridLayoutManager)?.findFirstVisibleItemPosition() ?: 0
        )
    }

    private fun setBackArrow(){
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        const val KEY_ARGS = "CharacterFragment"
        const val POSITION = "position"
    }
}