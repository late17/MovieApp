package com.example.myapplication.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.myapplication.data.model.MovieDetails
import com.example.myapplication.data.model.PageModel
import com.example.myapplication.data.remote.MovieAPI
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class PageRepository(private val movieAPI: MovieAPI)  {

    private val _downloadedPageResponse =  MutableLiveData<PageModel>()
    private val downloadedPageResponse: LiveData<PageModel>
        get() = _downloadedPageResponse

    fun fetchPage(compositeDisposable: CompositeDisposable, pageNumber : Int): LiveData<PageModel> {
        try {
            compositeDisposable.add(
                movieAPI.getPopular(pageNumber)
                    .subscribeOn(Schedulers.io())
                    .subscribe(
                        {
                            _downloadedPageResponse.postValue(it)
                        },
                        {
                            it.message?.let { it1 -> Log.e("MovieDetailsDataSource", it1) }
                        }
                    )
            )

        }

        catch (e: Exception){
            e.message?.let { Log.e("MovieDetailsDataSource", it) }
        }

        return downloadedPageResponse
    }
}