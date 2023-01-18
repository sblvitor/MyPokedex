package com.lira.mypokedex.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.lira.mypokedex.core.createDialog
import com.lira.mypokedex.core.createProgressDialog
import com.lira.mypokedex.databinding.FragmentFavoritesBinding
import com.lira.mypokedex.presentation.FavoritesViewModel
import com.lira.mypokedex.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private val favoritesViewModel by viewModel<FavoritesViewModel>()
    private val dialog by lazy { createProgressDialog() }
    private val adapter by lazy { FavPokemonAdapter() }
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