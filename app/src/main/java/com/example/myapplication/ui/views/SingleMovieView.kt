package com.example.myapplication.ui.views

import com.example.myapplication.ui.viewModels.MovieDetailsViewModel

interface SingleMovieView {
    fun setData(movieDetails: MovieDetailsViewModel)
}