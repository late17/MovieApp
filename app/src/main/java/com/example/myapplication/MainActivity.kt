package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.adapters.MainActivityViewModel
import com.example.myapplication.data.adapters.MoviePagedListRepository
import com.example.myapplication.data.remote.MovieAPI
import com.example.myapplication.data.remote.MovieAPIClient
import com.example.myapplication.data.adapters.PopularMoviePagedListAdapter
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.repositories.NetworkState

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainActivityViewModel

    lateinit var movieRepository: MoviePagedListRepository

    private lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recylerView)

        val apiService : MovieAPI = MovieAPIClient().getClient()

        movieRepository = MoviePagedListRepository(apiService)

        viewModel = MainActivityViewModel(movieRepository)


        val movieAdapter = PopularMoviePagedListAdapter(this)

        val linearLayoutManager = LinearLayoutManager(this)



        recyclerView.layoutManager = linearLayoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter =  movieAdapter as PagedListAdapter<Movie, RecyclerView.ViewHolder>



        viewModel.moviePagedList.observe(this, Observer {
            movieAdapter.submitList(it)
        })

        viewModel.networkState.observe(this, Observer {
            findViewById<ProgressBar>(R.id.progressBar).visibility = if (viewModel.listIsEmpty() && it == NetworkState.LOADING) View.VISIBLE else View.GONE
            findViewById<TextView>(R.id.error_msg_item).visibility = if (viewModel.listIsEmpty() && it == NetworkState.ERROR) View.VISIBLE else View.GONE

            if (!viewModel.listIsEmpty()) {
                movieAdapter.setNetworkState(it)
            }
        })

    }




//    private lateinit var recyclerView : RecyclerView;
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        recyclerView = findViewById(R.id.recylerView)
//
//        val layoutManager = LinearLayoutManager(this)
//        val adapter = MyPageAdapter(this)
//
//        recyclerView.layoutManager = layoutManager
//        recyclerView.adapter = adapter
//
////
////        val btn = findViewById<Button>(R.id.button)
////        val text = findViewById<TextView>(R.id.num)
////        btn.setOnClickListener(View.OnClickListener {
////            val intent = Intent(this, SingleMovieActivity::class.java)
////            intent.putExtra("movieId", Integer.parseInt(text.text.toString()))
////            startActivity(intent)
////        })
//    }
}