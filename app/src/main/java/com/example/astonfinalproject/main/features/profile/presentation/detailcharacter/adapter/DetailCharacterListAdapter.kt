package com.example.astonfinalproject.main.features.profile.presentation.detailcharacter.adapter

/*
class DetailCharacterListAdapter:
    androidx.recyclerview.widget.ListAdapter<DetailCharacterModel,  DetailCharacterListAdapter.DetailCharacterViewHolder>(
    DetailCharacterDiffUtil
){

    class  DetailCharacterViewHolder(private val view: View): RecyclerView.ViewHolder(view){

        fun bind(model:  DetailCharacterModel){
            view.findViewById<TextView>(R.id.character_name_textView).text = model.name
            view.findViewById<TextView>(R.id.character_name_textView).text = model.status
            view.findViewById<TextView>(R.id.character_name_textView).text = model.species
            view.findViewById<TextView>(R.id.character_name_textView).text = model.gender
            setImage(view.context,"https://rickandmortyapi.com/api/character/avatar/1.jpeg", view.findViewById(
                R.id.character_imageView))
        }

        private fun setImage(context: Context, url: String, imageView: ImageView){
            Glide
                .with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int):  DetailCharacterListAdapter. DetailCharacterViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_character, viewGroup, false)
        return  DetailCharacterListAdapter. DetailCharacterViewHolder(view)
    }

    override fun onBindViewHolder(holder:  DetailCharacterListAdapter. DetailCharacterViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)

    }
}*/
