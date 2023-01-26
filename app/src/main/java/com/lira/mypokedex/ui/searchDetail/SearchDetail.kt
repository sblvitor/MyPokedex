package com.lira.mypokedex.ui.searchDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.lira.mypokedex.R
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.databinding.FragmentSearchDetailBinding
import com.lira.mypokedex.presentation.SearchDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchDetail : Fragment() {

    private val searchDetailViewModel by viewModel<SearchDetailViewModel>()

    private var _binding: FragmentSearchDetailBinding? = null

    private val binding get() = _binding!!

    private val args: SearchDetailArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentSearchDetailBinding.inflate(inflater, container, false)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemon = args.pokemon

        setupUI(pokemon)
    }
    
    private fun setupUI(pokemon: Pokemon) {
        binding.apply {
            if(pokemon.sprites.versions.generationV.blackWhite.animated.frontDefault != null)
                Glide.with(requireContext())
                    .asGif()
                    .load(pokemon.sprites.versions.generationV.blackWhite.animated.frontDefault)
                    .into(ivSearchDetail)
            else
                Glide.with(requireContext())
                    .load(pokemon.sprites.frontDefault)
                    .into(ivSearchDetail)

            tvSearchNameDetail.text = pokemon.name.replaceFirstChar { it.uppercase() }
            tvPokemonSearchNumber.text = pokemon.id.toString()

            when(pokemon.types[0].type.name) {
                "fire" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.fogo)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.fire)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "water" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.agua)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.agua)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "grass" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.grama)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.grama)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "flying" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.voador)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.voador)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "poison" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.poison)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.poison)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "bug" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.inseto)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.inseto)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "ghost" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.fantasma)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.fantasma)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "steel" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.aco)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.aco)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "ice" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.gelo)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.ice)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "normal" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.normal)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.normal)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "fighting" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.lutador)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.lutador)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "rock" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.pedra)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.rock)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "ground" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.terra)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.ground)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "fairy" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.fada)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.fada)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "psychic" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.psiquico)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.psiquico)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "dark" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.dark)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.dark)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "dragon" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.dragao)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.dragao)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "electric" -> {
                    searchChipTypeOne.setChipIconResource(R.drawable.eletrico)
                    searchChipTypeOne.setChipBackgroundColorResource(R.color.eletrico)
                    searchChipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
            }

            if(pokemon.types.size > 1) {
                searchChipTypeTwo.visibility = View.VISIBLE
                when(pokemon.types[1].type.name) {
                    "fire" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.fogo)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.fire)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "water" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.agua)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.agua)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "grass" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.grama)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.grama)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "flying" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.voador)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.voador)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "poison" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.poison)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.poison)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "bug" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.inseto)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.inseto)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "ghost" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.fantasma)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.fantasma)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "steel" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.aco)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.aco)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "ice" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.gelo)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.ice)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "normal" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.normal)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.normal)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "fighting" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.lutador)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.lutador)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "rock" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.pedra)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.rock)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "ground" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.terra)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.ground)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "fairy" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.fada)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.fada)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "psychic" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.psiquico)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.psiquico)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "dark" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.dark)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.dark)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "dragon" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.dragao)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.dragao)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "electric" -> {
                        searchChipTypeTwo.setChipIconResource(R.drawable.eletrico)
                        searchChipTypeTwo.setChipBackgroundColorResource(R.color.eletrico)
                        searchChipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                }
            }

            val heightValue = (pokemon.height * 10).toString() + " " + "cm"
            tvSearchHeightValue.text = heightValue

            val weightValue = (pokemon.weight / 10F).toString() + " " + "kg"
            tvSearchWeightValue.text = weightValue
        }
    }

}