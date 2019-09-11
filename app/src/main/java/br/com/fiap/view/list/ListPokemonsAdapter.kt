package br.com.fiap.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fiap.R
import br.com.fiap.model.Pokemon
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.view.*
import kotlinx.android.synthetic.main.activity_detail.view.ivPokemon
import kotlinx.android.synthetic.main.activity_detail.view.tvPokemonName
import kotlinx.android.synthetic.main.pokemon_list_item.view.*

class ListPokemonsAdapter(
    val pokemons: List<Pokemon>,
    val picasso: Picasso,
    val clickListener: (Pokemon) -> Unit
) : RecyclerView.Adapter<ListPokemonsAdapter.PokemonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_list_item, parent, false)
        return PokemonViewHolder(view)
    }
    override fun getItemCount(): Int {
        return pokemons.size
    }
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = pokemons[position]
        holder.bindView(pokemon, picasso, clickListener)
    }

    class PokemonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(pokemon: Pokemon,
                     picasso: Picasso,
                     clickListener: (Pokemon) -> Unit) = with(itemView) {
            tvPokemonName.text = pokemon.name
            tvPokemonNumber.text = pokemon.number
            picasso.load("https://pokedexdx.herokuapp.com${pokemon.imageURL}").into(ivPokemon)

            setOnClickListener { clickListener(pokemon) }
        }
    }
}
