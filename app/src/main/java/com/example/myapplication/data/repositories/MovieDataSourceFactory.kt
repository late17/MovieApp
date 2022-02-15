package com.example.myapplication.data.repositories

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.remote.MovieAPI
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory (private val apiService : MovieAPI, private val compositeDisposable: CompositeDisposable)
    : DataSource.Factory<Int, Movie>() {

    val moviesLiveDataSource =  MutableLiveData<MovieDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val movieDataSource = MovieDataSource(apiService,compositeDisposable)

        moviesLiveDataSource.postValue(movieDataSource)
        return movieDataSource
    }
}