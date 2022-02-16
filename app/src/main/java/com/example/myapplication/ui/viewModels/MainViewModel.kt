package com.example.myapplication.ui.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.repositories.PageRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest

@OptIn(FlowPreview::class, kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class MainViewModel : ViewModel() {
    private val pageRepository = PageRepository()

    val movieFlow: Flow<PagingData<Movie>>

    private val data = MutableLiveData("")

    init {
        movieFlow = data.asFlow()
            // if user types text too quickly -> filtering intermediate values to avoid excess loads
            .debounce(500)
            .flatMapLatest {
                pageRepository.getPagedMovies()
            }
            // always use cacheIn operator for flows returned by Pager. Otherwise exception may be thrown
            // when 1) refreshing/invalidating or 2) subscribing to the flow more than once.
            .cachedIn(viewModelScope)
    }




}