package com.example.myapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.adapters.PageAdapter
import com.example.myapplication.presenters.MainActivityPresenter
import com.example.myapplication.ui.viewModels.MainViewModel
import com.example.myapplication.ui.views.MainActivityView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var mainActivityPresenter : MainActivityPresenter

    private lateinit var recyclerView : RecyclerView

    private val viewModel by viewModels<MainViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityPresenter = MainActivityPresenter(this)

        setupUsersList()
        settingUpBar()
    }

    private fun settingUpBar() {
        // assigning ID of the toolbar to a variable
        val toolbar = findViewById<Toolbar>(R.id.main_activity_toolbar)

        // using toolbar as ActionBar
        setSupportActionBar(toolbar)
        supportActionBar?.title = "MovieApp"
    }

    private fun setupUsersList() {
        val adapter = PageAdapter(this)

        recyclerView = findViewById(R.id.recycler_view)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        observeUsers(adapter)
    }

    private fun observeUsers(adapter: PageAdapter) {
        lifecycleScope.launch {
            viewModel.movieFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}