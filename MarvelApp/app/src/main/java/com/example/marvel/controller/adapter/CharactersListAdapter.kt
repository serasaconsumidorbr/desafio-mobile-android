package com.example.marvel.controller.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.marvel.R
import com.example.marvel.controller.CharactersActivity
import com.example.marvel.controller.callback.Callback
import com.example.marvel.model.Characters
import com.squareup.picasso.Picasso

class CharactersListAdapter(val list: List<Characters>, val context: Context) :
    RecyclerView.Adapter<CharactersListAdapter.ViewHolder>() {

    class ViewHolder(var itemView : View) : RecyclerView.ViewHolder(itemView) {
        val card: CardView = itemView.findViewById(R.id.mcv_item_characters)
        val name: TextView = itemView.findViewById(R.id.tv_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_characters_list, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val characterData: Characters = list[position]

        holder.name.text = characterData.name
        holder.card.setOnClickListener{
            (context as Callback).itemSelected(characterData)
        }

    }

    override fun getItemCount(): Int = list.size

}

