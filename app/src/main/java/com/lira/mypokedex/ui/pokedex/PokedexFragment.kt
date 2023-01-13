package com.lira.mypokedex.ui.pokedex

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
import com.lira.mypokedex.databinding.FragmentPokedexBinding
import com.lira.mypokedex.presentation.PokedexViewModel
import com.lira.mypokedex.ui.MainActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment() {

    private val pokedexViewModel by viewModel<PokedexViewModel>()
    private val dialog by lazy { createProgressDialog() }
    private val adapter by lazy { PokemonAdapter() }
    private var _binding: FragmentPokedexBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentPokedexBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupActionBar()

        binding.rvPokemon.adapter = adapter

        pokedexViewModel.pokemon.observe(viewLifecycleOwner){
            when(it){
                PokedexViewModel.State.Loading -> {
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    dialog.show()
                }
                is PokedexViewModel.State.Error -> {
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                    dialog.dismiss()
                }
                is PokedexViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.pokemonList)
                }
            }
        }
    }

    private fun setupActionBar() {
        (requireActivity() as MainActivity).setSupportActionBar(binding.pokedexToolbar)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}