package com.example.myapplication.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.MovieDetails
import com.example.myapplication.data.remote.MovieAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MovieRepository(private val movieAPI: MovieAPI) {

    private val _networkState  = MutableLiveData<NetworkState>()
    val networkState: LiveData<NetworkState>
        get() = _networkState                   //with this get, no need to implement get function to get networkSate

    private val _downloadedMovieDetailsResponse =  MutableLiveData<MovieDetails>()
    private val downloadedMovieResponse: LiveData<MovieDetails>
        get() = _downloadedMovieDetailsResponse

    fun fetchMovieDetails(movieId: Int, compositeDisposable: CompositeDisposable): LiveData<MovieDetails> {

        _networkState.postValue(NetworkState.LOADING)

        try {
            compositeDisposable.add(
                movieAPI.getMovieDetailsById(movieId)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _networkState.postValue(NetworkState.LOADED)
                            _downloadedMovieDetailsResponse.postValue(it)
                        },
                        {
                            _networkState.postValue(NetworkState.ERROR)
                            it.message?.let { it1 -> Log.e("MovieDetailsDataSource", it1) }

                        }
                    )
            )

        }

        catch (e: Exception){
            e.message?.let { Log.e("MovieDetailsDataSource", it) }
        }

        return downloadedMovieResponse
    }

}