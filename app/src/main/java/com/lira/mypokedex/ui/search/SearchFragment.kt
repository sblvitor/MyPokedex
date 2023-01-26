package com.lira.mypokedex.ui.search

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
import com.lira.mypokedex.databinding.FragmentSearchBinding
import com.lira.mypokedex.presentation.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private val searchViewModel by viewModel<SearchViewModel>()
    private val adapter by lazy { SearchAdapter() }
    private val dialog by lazy { createProgressDialog() }
    private var _binding: FragmentSearchBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupMenu()

        binding.rvPokemonSearch.adapter = adapter

        searchViewModel.searchedPokemon.observe(viewLifecycleOwner) {
            when(it) {
                SearchViewModel.State.Loading -> {
                    dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
                    dialog.show()
                }
                is SearchViewModel.State.Error -> {
                    dialog.dismiss()
                    createDialog {
                        setMessage(it.error.message)
                    }.show()
                }
                is SearchViewModel.State.Success -> {
                    dialog.dismiss()
                    adapter.submitList(it.pokemonList)
                }
            }
        }

    }

    private fun setupMenu() {
        binding.searchToolbar.inflateMenu(R.menu.search_menu)
        val searchView = binding.searchToolbar.menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setOnQueryTextListener(this)

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, object: OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if(!searchView.isIconified) {
                    binding.searchToolbar.collapseActionView()
                } else {
                    this.isEnabled = false
                    //activity?.finish()
                }
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { searchViewModel.searchPokemonByName(it) }
        binding.root.hideSoftKeyBoard()
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}