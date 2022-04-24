package dev.ribeiro.bruno.desafioandroid.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import dev.ribeiro.bruno.desafioandroid.R
import dev.ribeiro.bruno.desafioandroid.databinding.RecyclerCharacterItemBinding
import dev.ribeiro.bruno.desafioandroid.domain.entities.CharacterDetail
import dev.ribeiro.bruno.desafioandroid.domain.entities.Thumbnail

class CharacterAdapter(
    val passItem: (CharacterDetail) -> (Unit)
) : PagingDataAdapter<CharacterDetail, CharacterAdapter.CharacterViewHolder>(CharacterDifferentiator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val inflateBinding =
            RecyclerCharacterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return CharacterViewHolder(inflateBinding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            passItem(item)
        }
    }

    inner class CharacterViewHolder(private val binding: RecyclerCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(characterDetail: CharacterDetail) {
            with(characterDetail) {
                binding.tvCharacterName.text = name.verifyText()
                binding.tvCharacterDescription.text = description.verifyText()
                setImage(thumbnail, binding.root.context)
            }
        }

        private fun String?.verifyText(): String {
            return if(this.isNullOrEmpty()) "Informação indisponível." else this
        }

        private fun setImage(character: Thumbnail?, context: Context) {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.setColorSchemeColors(context.resources.getColor(R.color.purple_200))
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(context)
                .load("${character?.path}.${character?.extension}")
                .placeholder(circularProgressDrawable)
                .error(R.drawable.placeholder_couple_superhero)
                .into(binding.ivCharacterThumb)
        }
    }

    object CharacterDifferentiator : DiffUtil.ItemCallback<CharacterDetail>() {

        override fun areItemsTheSame(oldItem: CharacterDetail, newItem: CharacterDetail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CharacterDetail, newItem: CharacterDetail): Boolean {
            return oldItem == newItem
        }
    }
}