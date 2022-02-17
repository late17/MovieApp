package com.example.myapplication.data.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.remote.POSTER_BASE_URL
import com.example.myapplication.ui.activities.SingleMovieActivity

class PageAdapter(private val context: Context) : PagingDataAdapter<Movie, PageAdapter.MovieViewHolder>(ArticleDiffItemCallback)  {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position), context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(layoutInflater.inflate(R.layout.fragment_movie, parent, false))
    }

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private var movieCard = itemView.findViewById<LinearLayout>(R.id.movieCard)
        private var title = itemView.findViewById<TextView>(R.id.main_movie_title)
        private var poster = itemView.findViewById<ImageView>(R.id.main_movie_poster)

        fun bind(movie : Movie?, context: Context){
            title.text = movie?.title
            val moviePosterURL = POSTER_BASE_URL + movie?.posterPath
            Glide.with(itemView.context)
                .load(moviePosterURL)
                .into(poster)

            movieCard.setOnClickListener {
                val intent = Intent(context, SingleMovieActivity::class.java)
                if (movie != null) {
                    intent.putExtra("movieId", movie.id)
                }
                context.startActivity(intent)
            }
        }


    }
}

private object ArticleDiffItemCallback : DiffUtil.ItemCallback<Movie>() {

    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.posterPath == newItem.posterPath
    }
}