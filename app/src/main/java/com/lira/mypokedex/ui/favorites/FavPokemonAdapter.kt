package com.lira.mypokedex.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lira.mypokedex.data.model.PokemonDB
import com.lira.mypokedex.databinding.ItemPokemonFavBinding

class FavPokemonAdapter(private val onClickListener: OnClickListener): ListAdapter<PokemonDB, FavPokemonAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPokemonFavBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemPokemonFavBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PokemonDB) {
            binding.tvPokemonNameFav.text = item.name.replaceFirstChar { it.uppercase() }
            Glide
                .with(binding.root.context)
                .load(item.img)
                .into(binding.ivPokemonFav)

            binding.ibFavorite.setOnClickListener {
                onClickListener.onClick(item)
            }

            itemView.setOnClickListener {
                val action = FavoritesFragmentDirections.actionNavigationFavoritesToNavigationPokemonDetail(item.name)
                it.findNavController().navigate(action)
            }
        }

    }

    class DiffCallback: DiffUtil.ItemCallback<PokemonDB>() {
        override fun areItemsTheSame(oldItem: PokemonDB, newItem: PokemonDB) = oldItem == newItem

        override fun areContentsTheSame(oldItem: PokemonDB, newItem: PokemonDB) = oldItem.id == newItem.id
    }

    class OnClickListener(val clickListener: (pokemon: PokemonDB) -> Unit) {
        fun onClick(pokemon: PokemonDB) = clickListener(pokemon)
    }

}