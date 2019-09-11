package br.com.fiap.view.form

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView
import androidx.lifecycle.ViewModel
import br.com.fiap.R
import br.com.fiap.model.Pokemon
import br.com.fiap.utils.URLProvider
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_form_pokemon.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named

class FormPokemonActivity : AppCompatActivity() {

    val formPokemonViewModel : FormPokemonViewModel by viewModel()
    val baseUrl : String by inject(named("baseURL"))
    val picasso: Picasso by inject()

    lateinit var pokemon : Pokemon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_pokemon)

        setValues()
    }

    private fun setValues() {
        pokemon = intent.getParcelableExtra("POKEMON")
        tvPokemonNameForm.text = pokemon.name
        //picasso.load(URLProvider.baseURL + pokemon.imageURL)
        picasso.load("$baseUrl${pokemon.imageURL}").into(ivPokemonForm)

        setSeekBar (sbAttack, tvAttackValue, pokemon.attack)
        setSeekBar(sbDefense, tvDefenseValue, pokemon.defense)
        setSeekBar(sbPS, tvPSValue, pokemon.ps)
        setSeekBar(sbVelocity, tvVelocityValue, pokemon.velocity)
    }

    private fun setSeekBar(seekBar: SeekBar, textView: TextView, value: Int) {
        seekBar.progress = value
        textView.text = value.toString()
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                textView.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
