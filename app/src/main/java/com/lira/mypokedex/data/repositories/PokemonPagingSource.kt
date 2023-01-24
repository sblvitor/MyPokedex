package com.lira.mypokedex.data.repositories

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.lira.mypokedex.data.model.PokemonList
import com.lira.mypokedex.data.services.PokemonService
import retrofit2.HttpException

class PokemonPagingSource(private val service: PokemonService): PagingSource<Int, PokemonList>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PokemonList> {
        return try {
            val currentPage = params.key ?: 0
            val response = service.listPokemon(currentPage)
            val responseData = response.results

            val pokedexEntries = responseData.mapIndexed { index, entry ->
                val number = if(entry.url.endsWith("/")) {
                    entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                } else {
                    entry.url.takeLastWhile { it.isDigit() }
                }
                val imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                PokemonList(entry.name, entry.url, imgUrl)
            }

            LoadResult.Page(
                data = pokedexEntries,
                prevKey = if(currentPage == 0) null else -20,
                nextKey = currentPage.plus(20)
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        } catch (httpE: HttpException) {
            LoadResult.Error(httpE)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, PokemonList>): Int? {
        return null
    }

}