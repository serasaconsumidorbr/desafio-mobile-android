package br.com.maceda.marvel.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.maceda.marvel.data.model.Character
import br.com.maceda.marvel.databinding.ItemCarouselCharacterBinding
import com.bumptech.glide.Glide

class CharacterCarouselAdapter : RecyclerView.Adapter<CharacterCarouselAdapter.ViewHolder>() {

    var onItemClickListener : ((Character) -> Unit)? = null

    private lateinit var binding: ItemCarouselCharacterBinding
    private val differCallBack = object : DiffUtil.ItemCallback<Character>(){
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.description == newItem.description &&
                    oldItem.thumbnail.path == newItem.thumbnail.path &&
                    oldItem.thumbnail.extension == newItem.thumbnail.extension
        }

    }
    private val differ = AsyncListDiffer(this,differCallBack)
    var characters: List<Character>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    inner class ViewHolder(binding: ItemCarouselCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        private val imgCharacter = binding.imgCharacter

        fun bindView(character: Character) {
            Glide.with(itemView.context)
                .load(character.thumbnail.path+"."+character.thumbnail.extension)
                .into(imgCharacter)

            itemView.setOnClickListener {
                onItemClickListener?.invoke(character)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCarouselCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val character = characters[position]
        viewHolder.bindView(character)
    }
    override fun getItemCount() = characters.size

}