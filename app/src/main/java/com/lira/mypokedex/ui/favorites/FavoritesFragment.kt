package com.lira.mypokedex.ui.favorites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lira.mypokedex.core.createDialog
import com.lira.mypokedex.core.createProgressDialog
import com.lira.mypokedex.databinding.FragmentFavoritesBinding
import com.lira.mypokedex.presentation.FavoritesViewModel
import com.lira.mypokedex.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private val favoritesViewModel by viewModel<FavoritesViewModel>()
    private val dialog by lazy { createProgressDialog() }
    private lateinit var adapter:  FavPokemonAdapter
    private var _binding: FragmentFavoritesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionBar()

        adapter = FavPokemonAdapter(FavPokemonAdapter.OnClickListener { pokemon ->
            favoritesViewModel.deleteFavPokemon(pokemon)
        })
        binding.rvFavoritesPokemon.adapter = adapter

        favoritesViewModel.favPokemon.observe(viewLifecycleOwner) {
            when(it) {
                FavoritesViewModel.State.Loading -> {
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    dialog.show()
                }
                is FavoritesViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is FavoritesViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.pokemonList)
                    if (adapter.itemCount == 0){
                        binding.tvNoFavPok.visibility = View.VISIBLE
                    }
                }
                is FavoritesViewModel.State.JustDeleted -> {
                    if (it.ok)
                        Log.d("TAG", "Deletado")
                }
            }
        }
    }

    private fun setupActionBar() {
        (requireActivity() as MainActivity).setSupportActionBar(binding.favoritesToolbar)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}