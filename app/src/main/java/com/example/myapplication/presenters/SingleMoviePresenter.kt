package com.example.myapplication.presenters



import com.example.myapplication.services.MovieService
import com.example.myapplication.ui.views.SingleMovieView

class SingleMoviePresenter(view: SingleMovieView, movieId: Int) {
    init {
        val movieService = MovieService()
        val viewModel = movieService.getMovie(movieId)
        view.setData(viewModel)
    }

}

