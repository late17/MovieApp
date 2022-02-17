package com.example.myapplication.ui.viewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.MovieDetails
import com.example.myapplication.data.repositories.MovieRepository
import com.example.myapplication.data.repositories.NetworkState
import io.reactivex.disposables.CompositeDisposable

class MovieDetailsViewModel(private val movieRepository: MovieRepository, movieId: Int) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val  movieDetails : LiveData<MovieDetails> by lazy {
        movieRepository.fetchMovieDetails(movieId, compositeDisposable)
    }

    val  networkState : LiveData<NetworkState> by lazy {
        movieRepository.networkState
    }



    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}