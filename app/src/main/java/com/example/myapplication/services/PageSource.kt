package com.example.myapplication.services

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.remote.MovieAPI



class PageSource(private val movieAPI: MovieAPI) : PagingSource<Int, Movie>() {


    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition ?: return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {

        val page = params.key ?: 1

            val response = movieAPI.getPopular(page)
            val movies = response.results
            //val movie = Movie(listOf(3,4), "","","title1")

            return LoadResult.Page(
                data = movies,
                prevKey = if (page >1) page-1 else null,
                nextKey = if (movies.size == params.loadSize) page + (params.loadSize / 20) else null
            )

    }
}