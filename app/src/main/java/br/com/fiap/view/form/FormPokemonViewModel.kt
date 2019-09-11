package br.com.fiap.view.form

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.model.Pokemon
import br.com.fiap.repository.PokemonRepository

class FormPokemonViewModel (
   val pokemonRepository: PokemonRepository
) : ViewModel(){

    val isLoading = MutableLiveData<Boolean>()
    val messageResponse = MutableLiveData<String>()

    fun updatePokemon(pokemon : Pokemon){
        isLoading.value = true
        messageResponse.value = "Dados gravado com sucesso"
        pokemonRepository.updatePokemon(pokemon,{
            isLoading.value = false
            messageResponse.value = "Dados gravado com sucesso"
        },{
            isLoading.value = false
            messageResponse.value = it?.message
        })
    }
}