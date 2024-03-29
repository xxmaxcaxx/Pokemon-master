package br.com.fiap.view.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.fiap.R
import br.com.fiap.view.detail.DetailActivity
import br.com.fiap.view.form.FormPokemonActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_list_pokemons.*
import kotlinx.android.synthetic.main.include_loading.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class ListPokemonsActivity : AppCompatActivity() {

    val listaPokemonsViewModel: ListPokemonsViewModel by viewModel()

    val picasso:Picasso by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_pokemons)

        listaPokemonsViewModel.getPokemons()

        listaPokemonsViewModel.isLoading.observe(this, Observer {
            if(it == true) {
                containerLoading.visibility = View.VISIBLE
            } else {
                containerLoading.visibility = View.GONE
            }
        })
        listaPokemonsViewModel.messageError.observe(this, Observer {
            if(it != "") {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        })
        listaPokemonsViewModel.pokemons.observe(this, Observer {
            rvPokemons.adapter = ListPokemonsAdapter(
                it, picasso
            ) {
                val detailActivity = Intent(this, FormPokemonActivity::class.java)
                detailActivity.putExtra("POKEMON", it)
                startActivity(detailActivity)
            }

            rvPokemons.layoutManager = GridLayoutManager (this, 3)
        })
    }
}

