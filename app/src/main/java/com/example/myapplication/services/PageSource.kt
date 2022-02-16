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

        return try {
            val users = movieAPI.getPopular(page)


            return LoadResult.Page(
                data = users,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (users.size == params.loadSize) page + (params.loadSize / 20) else null
            )
        } catch (e: Exception) {
            LoadResult.Error(throwable = e)
        }
    }
}