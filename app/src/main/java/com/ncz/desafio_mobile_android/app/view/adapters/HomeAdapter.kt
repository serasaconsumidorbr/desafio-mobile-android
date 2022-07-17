package com.ncz.desafio_mobile_android.app.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ncz.desafio_mobile_android.R
import com.ncz.desafio_mobile_android.databinding.CardCharacterItemBinding
import com.ncz.desafio_mobile_android.databinding.CardHeroItemBinding
import com.ncz.desafio_mobile_android.domain.entities.HomeList

class HomeAdapter(private val homeList: HomeList) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder: RecyclerView.ViewHolder = if (viewType == 0) {
            val binding = CardHeroItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            ViewHolderHeroes(binding)
        } else {
            val binding = CardCharacterItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            ViewHolderCharacters(binding)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (position == 0) {
            holder as ViewHolderHeroes
            holder.rclHeroes.apply {
                layoutManager = LinearLayoutManager(
                    holder.itemView.context,
                    LinearLayoutManager.HORIZONTAL, false
                )
                setHasFixedSize(true)
                adapter = homeList.heroes?.let { heroes -> HeroesAdapter(heroes) }
            }
        } else {
            holder as ViewHolderCharacters
            holder.rclCharacter.apply {
                layoutManager = LinearLayoutManager(
                    holder.itemView.context,
                    LinearLayoutManager.VERTICAL, false
                )
                setHasFixedSize(true)
                adapter = homeList.character?.let { character -> CharactersAdapter(character) }
            }
        }
    }

    override fun getItemCount(): Int = 2

    inner class ViewHolderCharacters(binding: CardCharacterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rclCharacter: RecyclerView = itemView.findViewById(R.id.recyclerCharacter)
    }

    inner class ViewHolderHeroes(binding: CardHeroItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rclHeroes: RecyclerView = itemView.findViewById(R.id.recyclerHeroes)
    }

}