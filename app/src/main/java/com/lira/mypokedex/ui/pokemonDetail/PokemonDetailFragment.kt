package com.lira.mypokedex.ui.pokemonDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.lira.mypokedex.R
import com.lira.mypokedex.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val args: PokemonDetailFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemon = args.pokemon

        binding.apply {
            Glide.with(requireContext())
                .asGif()
                .load(pokemon.sprites.versions.generationV.blackWhite.animated.frontDefault)
                .into(ivPokemonDetail)

            tvPokemonNameDetail.text = pokemon.name.replaceFirstChar { it.uppercase() }
            tvPokemonNumber.text = pokemon.id.toString()

            when(pokemon.types[0].type.name) {
                "fire" -> {
                    chipTypeOne.setChipIconResource(R.drawable.fogo)
                    chipTypeOne.setChipBackgroundColorResource(R.color.fire)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "water" -> {
                    chipTypeOne.setChipIconResource(R.drawable.agua)
                    chipTypeOne.setChipBackgroundColorResource(R.color.agua)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "grass" -> {
                    chipTypeOne.setChipIconResource(R.drawable.grama)
                    chipTypeOne.setChipBackgroundColorResource(R.color.grama)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "flying" -> {
                    chipTypeOne.setChipIconResource(R.drawable.voador)
                    chipTypeOne.setChipBackgroundColorResource(R.color.voador)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "poison" -> {
                    chipTypeOne.setChipIconResource(R.drawable.poison)
                    chipTypeOne.setChipBackgroundColorResource(R.color.poison)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "bug" -> {
                    chipTypeOne.setChipIconResource(R.drawable.inseto)
                    chipTypeOne.setChipBackgroundColorResource(R.color.inseto)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
            }

            if(pokemon.types.size > 1) {
                chipTypeTwo.visibility = View.VISIBLE
                when(pokemon.types[1].type.name) {
                    "fire" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.fogo)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.fire)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "water" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.agua)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.agua)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "grass" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.grama)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.grama)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "flying" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.voador)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.voador)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "poison" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.poison)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.poison)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "bug" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.inseto)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.inseto)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                }
            }

            val heightValue = pokemon.height.toString() + " " + "dm"
            tvHeightValue.text = heightValue

            val weightValue = (pokemon.weight / 10F).toString() + " " + "kg"
            tvWeightValue.text = weightValue
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}