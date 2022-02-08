package com.example.myapplication.presenters



import com.example.myapplication.services.MovieService
import com.example.myapplication.views.SingleMovieView

class SingleMoviePresenter(private val view: SingleMovieView) {
    fun onCreate(movieId:Int){
        val movieService = MovieService()
        val viewModel = movieService.getMovie(movieId)
        view.setData(viewModel)
    }
}

