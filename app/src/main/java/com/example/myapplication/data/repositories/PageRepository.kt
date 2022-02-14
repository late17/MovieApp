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
//
//
//    private val _networkState  = MutableLiveData<NetworkState>()
//    val networkState: LiveData<NetworkState>
//        get() = _networkState
//
//    private val _downloadedPageResponse =  MutableLiveData<PageModel>()
//    private val downloadedPageResponse: LiveData<PageModel>
//        get() = _downloadedPageResponse
//
//    fun fetchPage(compositeDisposable: CompositeDisposable, pageNumber: Int): LiveData<PageModel> {
//
//        _networkState.postValue(NetworkState.LOADING)
//
//        try {
//            compositeDisposable.add(
//                movieAPI.getPopular(pageNumber)
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(
//                        {
//                            _networkState.postValue(NetworkState.LOADED)
//                            _downloadedPageResponse.postValue(it)
//                        },
//                        {
//                            _networkState.postValue(NetworkState.ERROR)
//                            it.message?.let { it1 -> Log.e("MovieDetailsDataSource", it1) }
//                        }
//                    )
//            )
//
//        }
//
//        catch (e: Exception){
//            e.message?.let { Log.e("MovieDetailsDataSource", it) }
//        }
//
//        return downloadedPageResponse
//    }
//}