package com.example.myapplication.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.model.MovieDetails
import com.example.myapplication.data.model.PageModel
import com.example.myapplication.data.repositories.PageRepository
import com.example.myapplication.services.PageService
import io.reactivex.disposables.CompositeDisposable

class PageViewModel(private val pageRepository: PageRepository, page: Int) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    val  page : LiveData<PageModel> by lazy {
        pageRepository.fetchPage(compositeDisposable, page)
    }




    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}