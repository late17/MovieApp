package com.example.myapplication.views

import androidx.lifecycle.LiveData
import com.example.myapplication.data.model.MovieDetails
import com.example.myapplication.data.viewModel.MovieDetailsViewModel

interface SingleMovieView {
    fun setData(movieDetails: MovieDetailsViewModel)
}