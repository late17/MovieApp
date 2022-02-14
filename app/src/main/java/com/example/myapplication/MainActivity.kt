package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.adapters.DefaultLoadStateAdapter
import com.example.myapplication.data.adapters.PageAdapter
import com.example.myapplication.data.repositories.PageRepository
import com.example.myapplication.ui.viewModels.MainViewModel
import com.example.myapplication.views.MainActivityView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity(), MainActivityView {

    private lateinit var recyclerView : RecyclerView

    private lateinit var mainLoadStateHolder: DefaultLoadStateAdapter.Holder

    private val movieRepository = PageRepository()

    private val viewModel by viewModelCreator { MainViewModel(movieRepository) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val pageRep = PageSource(MovieAPIClient().getClient())

        //recyclerView = findViewById(R.id.recylerView)

//        val layoutManager = LinearLayoutManager(this)
//        recyclerView.layoutManager = layoutManager
//        val movieAdapter = PageAdapter(this)
//
//        recyclerView.layoutManager = layoutManager
//        recyclerView.setHasFixedSize(true)
//        recyclerView.adapter = movieAdapter


        setupUsersList()


//        val btn = findViewById<Button>(R.id.button)
//        val text = findViewById<TextView>(R.id.num)
//        btn.setOnClickListener(View.OnClickListener {
//            val intent = Intent(this, SingleMovieActivity::class.java)
//            intent.putExtra("movieId", Integer.parseInt(text.text.toString()))
//            startActivity(intent)
//        })
    }

    private fun setupUsersList() {
        val adapter = PageAdapter(this)

        val footerAdapter = DefaultLoadStateAdapter(this)

        // combined adapter which shows both the list of users + footer indicator when loading pages
        val adapterWithLoadState = adapter.withLoadStateFooter(footerAdapter)

        recyclerView = findViewById(R.id.recylerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterWithLoadState

        val layoutInflater = LayoutInflater.from(this)
        val itemView = layoutInflater.inflate(R.layout.load_state, recyclerView, false)
        mainLoadStateHolder = DefaultLoadStateAdapter.Holder(
            itemView
        )


        observeUsers(adapter)
        observeLoadState(adapter)

//        handleListVisibility(adapter)
    }

    private fun observeUsers(adapter: PageAdapter) {
        lifecycleScope.launch {
            viewModel.movieFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    private fun observeLoadState(adapter: PageAdapter) {
        // you can also use adapter.addLoadStateListener
        lifecycleScope.launch {
            adapter.loadStateFlow.debounce(200).collectLatest { state ->
                // main indicator in the center of the screen
                mainLoadStateHolder.bind(state.refresh)
            }
        }
    }
}