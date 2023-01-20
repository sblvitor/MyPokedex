package com.lira.mypokedex.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import com.lira.mypokedex.R
import com.lira.mypokedex.core.createDialog
import com.lira.mypokedex.core.createProgressDialog
import com.lira.mypokedex.core.hideSoftKeyBoard
import com.lira.mypokedex.databinding.FragmentPokedexBinding
import com.lira.mypokedex.presentation.PokedexViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment(), SearchView.OnQueryTextListener {

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

        setupMenu()

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

    private fun setupMenu() {
        binding.pokedexToolbar.inflateMenu(R.menu.search_menu)
        val searchView = binding.pokedexToolbar.menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(!searchView.isIconified) {
                    binding.pokedexToolbar.collapseActionView()
                } else {
                    this.isEnabled = false
                    pokedexViewModel.getTenPokemon()
                    //activity?.finish()
                }
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { pokedexViewModel.getPokemonByName(it) }
        binding.root.hideSoftKeyBoard()
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}