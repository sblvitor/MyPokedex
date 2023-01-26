package com.lira.mypokedex.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.lira.mypokedex.databinding.FragmentPokedexBinding
import com.lira.mypokedex.presentation.PokedexViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PokedexFragment : Fragment() {

    private val pokedexViewModel by viewModel<PokedexViewModel>()
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

        binding.rvPokemon.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            pokedexViewModel.pokemonList.collectLatest {
                adapter.submitData(it)
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            adapter.loadStateFlow.collect {
                val state = it.refresh
                binding.progressBarPokedex.isVisible = state is LoadState.Loading
            }
        }

        binding.rvPokemon.adapter = adapter.withLoadStateFooter(
            TryAgainAdapter{
                adapter.retry()
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}