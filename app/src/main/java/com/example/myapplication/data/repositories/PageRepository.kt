package com.example.myapplication.data.repositories


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.remote.MovieAPIClient
import com.example.myapplication.services.PageSource
import kotlinx.coroutines.flow.Flow

class PageRepository  {
     fun getPagedMovies(): Flow<PagingData<Movie>> {
         val movieAPI = MovieAPIClient().getClient()

        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { PageSource(movieAPI) }
        ).flow
    }

}
