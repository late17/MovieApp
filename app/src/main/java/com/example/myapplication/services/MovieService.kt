package com.example.myapplication.services

import com.example.myapplication.data.remote.MovieAPIClient
import com.example.myapplication.data.repositories.MovieRepository
import com.example.myapplication.ui.viewModels.MovieDetailsViewModel


class MovieService() {

    fun getMovie(movieId: Int): MovieDetailsViewModel {
        val movieAPIClient = MovieAPIClient()
        val movieAPI = movieAPIClient.getClient()
        val movieRepository = MovieRepository(movieAPI)

        return MovieDetailsViewModel(movieRepository, movieId)
    }

}