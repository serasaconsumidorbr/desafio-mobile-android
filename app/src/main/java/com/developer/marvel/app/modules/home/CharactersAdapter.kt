package com.developer.marvel.app.modules.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.developer.marvel.databinding.HomeTopItemBinding
import com.developer.marvel.domain.entities.Character
import com.developer.marvel.utils.loadImage

class CharactersAdapter :
    RecyclerView.Adapter<CharactersAdapter.ViewHolder>() {

    private val characters: ArrayList<Character> = arrayListOf()
    private var onClickItemListener: ((Character) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            HomeTopItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = characters[position]
        holder.binding.textCharacterName.text = character.name
        holder.binding.imageCharacter.contentDescription = "Foto de: ${character.name}"

        val baseUrl = character.thumbnail.let {
            "${it.path}/portrait_fantastic.${it.extension}"
        }
        holder.binding.imageCharacter.loadImage(baseUrl)

        holder.itemView.setOnClickListener {
            onClickItemListener?.invoke(character)
        }
    }

    override fun getItemCount() = characters.size

    fun addCharacter(character: Character) {
        characters.add(character)

        notifyItemInserted(characters.size)
    }

    fun addAllCharacters(characters: List<Character>) {
        val oldSize = this.characters.size
        this.characters.addAll(characters)
        val newSize = this.characters.size

        notifyItemRangeChanged(oldSize, newSize)
    }

    fun setOnClickItemListener(listener: (Character) -> Unit) {
        this.onClickItemListener = listener
    }

    inner class ViewHolder(val binding: HomeTopItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}