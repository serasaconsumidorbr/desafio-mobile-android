package br.com.maceda.marvel.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.maceda.marvel.data.model.Character
import br.com.maceda.marvel.databinding.ItemCharacterBinding
import com.bumptech.glide.Glide

class CharacterAdapter : RecyclerView.Adapter<CharacterAdapter.ViewHolder>() {

    var onItemClickListener : ((Character) -> Unit)? = null
    var onLastPosition : ((Int) -> Unit)? = null

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


    private lateinit var binding: ItemCharacterBinding

    inner class ViewHolder(binding: ItemCharacterBinding): RecyclerView.ViewHolder(binding.root) {
        private val txtName = binding.txtName
        private val txtDescription = binding.txtDescription
        private val imgCharacter = binding.imgCharacter

        fun bindView(character: Character) {
            txtName.text = character.name
            txtDescription.text = character.description

            Glide.with(itemView.context)
                .load("${character.thumbnail.path}.${character.thumbnail.extension}")
                .into(imgCharacter)

            itemView.setOnClickListener {
                onItemClickListener?.let { it(character) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        if (position >= itemCount -1)
            onLastPosition?.let { it(itemCount) }

        viewHolder.bindView(characters[position])
    }
    override fun getItemCount() = characters.size

}