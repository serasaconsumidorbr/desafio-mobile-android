package com.desafio.marvel.feature.characters.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.desafio.marvel.R
import com.desafio.marvel.feature.characters.domain.model.ResultsResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_characters.view.*

class CharactersAdapter(private val mCharactersList: MutableList<ResultsResponse>): RecyclerView.Adapter<CharactersAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view  = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_characters, parent, false)

        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int = mCharactersList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = mCharactersList[position]
        holder.bind(item)
    }

    fun updateItems(newItems: List<ResultsResponse>) {
        mCharactersList.addAll(newItems)
        notifyDataSetChanged()
    }

    inner class ItemViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(mCharactersList: ResultsResponse?){
            if (mCharactersList?.name.isNullOrEmpty())
                itemView.name.text = "Nome não informado!"
            else
                itemView.name.text = mCharactersList?.name

            if (mCharactersList?.description.isNullOrEmpty())
                itemView.description.text = "Descrição não informado!"
            else
                itemView.description.text = mCharactersList?.description

            Picasso.get()
                .load(mCharactersList?.thumbnail?.path + "." + mCharactersList?.thumbnail?.extension   )
                .error(R.drawable.ic_round_account_circle)
                .into(itemView.picture)
        }
    }
}