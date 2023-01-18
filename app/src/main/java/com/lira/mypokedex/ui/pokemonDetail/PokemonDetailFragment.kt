package com.lira.mypokedex.ui.pokemonDetail

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.lira.mypokedex.R
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.data.model.PokemonDB
import com.lira.mypokedex.databinding.FragmentPokemonDetailBinding
import com.lira.mypokedex.presentation.PokemonDetailViewModel
import com.lira.mypokedex.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokemonDetailFragment : Fragment() {

    private var _binding: FragmentPokemonDetailBinding? = null
    private val pokemonDetailViewModel by viewModel<PokemonDetailViewModel>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val args: PokemonDetailFragmentArgs by navArgs()

    private var favPokemon: PokemonDB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionBar()

        val pokemon = args.pokemon

        pokemonDetailViewModel.getFavoritePokemonById(pokemon.id)

        setupMenu(pokemon)

        pokemonDetailViewModel.favPokemon.observe(viewLifecycleOwner) {
            when (it) {
                is PokemonDetailViewModel.GetOrInsert.Get -> {
                    favPokemon = it.pokemon
                    requireActivity().invalidateOptionsMenu()
                }
                is PokemonDetailViewModel.GetOrInsert.Insert -> {
                    Log.d("TAG", "Inserido")
                }
            }
        }

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
                "ghost" -> {
                    chipTypeOne.setChipIconResource(R.drawable.fantasma)
                    chipTypeOne.setChipBackgroundColorResource(R.color.fantasma)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "steel" -> {
                    chipTypeOne.setChipIconResource(R.drawable.aco)
                    chipTypeOne.setChipBackgroundColorResource(R.color.aco)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "ice" -> {
                    chipTypeOne.setChipIconResource(R.drawable.gelo)
                    chipTypeOne.setChipBackgroundColorResource(R.color.ice)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "normal" -> {
                    chipTypeOne.setChipIconResource(R.drawable.normal)
                    chipTypeOne.setChipBackgroundColorResource(R.color.normal)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "fighting" -> {
                    chipTypeOne.setChipIconResource(R.drawable.lutador)
                    chipTypeOne.setChipBackgroundColorResource(R.color.lutador)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "rock" -> {
                    chipTypeOne.setChipIconResource(R.drawable.pedra)
                    chipTypeOne.setChipBackgroundColorResource(R.color.rock)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "ground" -> {
                    chipTypeOne.setChipIconResource(R.drawable.terra)
                    chipTypeOne.setChipBackgroundColorResource(R.color.ground)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "fairy" -> {
                    chipTypeOne.setChipIconResource(R.drawable.fada)
                    chipTypeOne.setChipBackgroundColorResource(R.color.fada)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "psychic" -> {
                    chipTypeOne.setChipIconResource(R.drawable.psiquico)
                    chipTypeOne.setChipBackgroundColorResource(R.color.psiquico)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "dark" -> {
                    chipTypeOne.setChipIconResource(R.drawable.dark)
                    chipTypeOne.setChipBackgroundColorResource(R.color.dark)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "dragon" -> {
                    chipTypeOne.setChipIconResource(R.drawable.dragao)
                    chipTypeOne.setChipBackgroundColorResource(R.color.dragao)
                    chipTypeOne.text = pokemon.types[0].type.name.replaceFirstChar { it.uppercase() }
                }
                "electric" -> {
                    chipTypeOne.setChipIconResource(R.drawable.eletrico)
                    chipTypeOne.setChipBackgroundColorResource(R.color.eletrico)
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
                    "ghost" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.fantasma)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.fantasma)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "steel" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.aco)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.aco)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "ice" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.gelo)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.ice)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "normal" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.normal)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.normal)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "fighting" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.lutador)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.lutador)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "rock" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.pedra)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.rock)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "ground" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.terra)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.ground)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "fairy" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.fada)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.fada)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "psychic" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.psiquico)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.psiquico)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "dark" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.dark)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.dark)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "dragon" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.dragao)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.dragao)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                    "electric" -> {
                        chipTypeTwo.setChipIconResource(R.drawable.eletrico)
                        chipTypeTwo.setChipBackgroundColorResource(R.color.eletrico)
                        chipTypeTwo.text = pokemon.types[1].type.name.replaceFirstChar { it.uppercase() }
                    }
                }
            }

            val heightValue = (pokemon.height * 10).toString() + " " + "cm"
            tvHeightValue.text = heightValue

            val weightValue = (pokemon.weight / 10F).toString() + " " + "kg"
            tvWeightValue.text = weightValue
        }
    }

    private fun setupActionBar() {
        (requireActivity() as MainActivity).setSupportActionBar(binding.pokemonDetailToolbar)
        binding.pokemonDetailToolbar.setNavigationOnClickListener {
            requireActivity().findNavController(R.id.nav_host_fragment_activity_main).navigateUp()
        }

    }

    private fun setupMenu(pokemon: Pokemon) {
        (requireActivity() as MenuHost).addMenuProvider(object: MenuProvider {

            override fun onPrepareMenu(menu: Menu) {
                super.onPrepareMenu(menu)
                if(favPokemon != null)
                    menu.findItem(R.id.favorite_button).icon = ResourcesCompat.getDrawable(resources, R.drawable.favorite_filled, null)
            }

            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.pokemon_detail_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                pokemonDetailViewModel.insertPokemon(PokemonDB(pokemon.id, pokemon.name, pokemon.sprites.frontDefault))
                menuItem.icon = ResourcesCompat.getDrawable(resources, R.drawable.favorite_filled, null)
                return true
            }

        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}