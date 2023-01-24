package com.lira.mypokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lira.mypokedex.data.model.PokemonList
import com.lira.mypokedex.databinding.ItemPokemonBinding

class PokemonAdapter: PagingDataAdapter<PokemonList, PokemonAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
        holder.setIsRecyclable(false)
    }

    inner class ViewHolder(private val binding: ItemPokemonBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PokemonList) {
            binding.tvPokemonName.text = item.name.replaceFirstChar { it.uppercase() }
            Glide
                .with(binding.root.context)
                .load(item.imgUrl)
                .into(binding.ivPokemon)

            itemView.setOnClickListener {
                val action = PokedexFragmentDirections.actionNavigationPokedexToNavigationPokemonDetail(item.name)
                it.findNavController().navigate(action)
            }

        }
    }

    class DiffCallback: DiffUtil.ItemCallback<PokemonList>() {
        override fun areItemsTheSame(oldItem: PokemonList, newItem: PokemonList) = oldItem == newItem

        override fun areContentsTheSame(oldItem: PokemonList, newItem: PokemonList) = oldItem.name == newItem.name
    }

}