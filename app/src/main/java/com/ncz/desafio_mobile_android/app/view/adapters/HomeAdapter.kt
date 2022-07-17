package com.ncz.desafio_mobile_android.app.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ncz.desafio_mobile_android.databinding.RecyclerCharactersBinding
import com.ncz.desafio_mobile_android.databinding.RecyclerHeroesBinding

import com.ncz.desafio_mobile_android.domain.entities.HomeList

class HomeAdapter(private val homeList: HomeList) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun getItemViewType(position: Int): Int = position

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val viewHolder: RecyclerView.ViewHolder = if (viewType == 0) {
            val binding = RecyclerHeroesBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            ViewHolderHeroes(binding)
        } else {
            val binding = RecyclerCharactersBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            ViewHolderCharacters(binding)
        }
        return viewHolder
    }

    override fun getItemCount(): Int = 2

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


    inner class ViewHolderCharacters(binding: RecyclerCharactersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rclCharacter: RecyclerView = binding.rclCharacter
    }

    inner class ViewHolderHeroes(binding: RecyclerHeroesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val rclHeroes: RecyclerView = binding.rclHeroes
    }

}