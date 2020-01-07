package com.issou.risidex.ui.list

import androidx.recyclerview.widget.RecyclerView
import com.issou.risidex.classe.Pokemon
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.issou.risidex.R
import com.squareup.picasso.Picasso

class ListAdapter(private val pokedex: List<Pokemon>) : RecyclerView.Adapter<ListAdapter.ViewHolder>(){

    class ViewHolder(val cardView: CardView) : RecyclerView.ViewHolder(cardView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ListAdapter.ViewHolder {

        val cardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false) as CardView

        return ViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //Nom
        var textViewNomPokemon = holder.cardView.findViewById<TextView>(R.id.primary_text)
        textViewNomPokemon.text = pokedex.get(position).name

        //Image
        var pokemonImage = holder.cardView.findViewById<ImageView>(R.id.pokemon_image)
        Picasso.with(holder.cardView.context).load(pokedex.get(position).image).into(pokemonImage)

        //Type 1
        var pokemonImageType1 = holder.cardView.findViewById<ImageView>(R.id.type1_image)
        Picasso.with(holder.cardView.context).load(pokedex.get(position).types.get(0).image).into(pokemonImageType1)

        //Type 2 (optionnel)
        if (pokedex.get(position).types.size > 1){
            var pokemonImageType2 = holder.cardView.findViewById<ImageView>(R.id.type2_image)
            Picasso.with(holder.cardView.context).load(pokedex.get(position).types.get(1).image).into(pokemonImageType2)
        }
    }

    override fun getItemCount() = pokedex.size
}