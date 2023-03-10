package com.lira.mypokedex.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lira.mypokedex.data.model.Pokemon
import com.lira.mypokedex.databinding.ItemPokemonBinding

class SearchAdapter: ListAdapter<Pokemon, SearchAdapter.ViewHolder>(DiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Pokemon) {
            binding.apply {
                tvPokemonName.text = item.name.replaceFirstChar { it.uppercase() }
                Glide
                    .with(binding.root.context)
                    .load(item.sprites.frontDefault)
                    .into(ivPokemon)
            }

            itemView.setOnClickListener {
                val action = SearchFragmentDirections.actionNavigationSearchToNavigationPokemonDetail(item.name)
                it.findNavController().navigate(action)
            }
        }

    }

    class DiffCallBack: DiffUtil.ItemCallback<Pokemon>() {

        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon) = oldItem.id == newItem.id

    }

}