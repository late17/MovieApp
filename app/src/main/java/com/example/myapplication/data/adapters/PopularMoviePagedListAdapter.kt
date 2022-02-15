package com.example.myapplication.data.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.remote.POSTER_BASE_URL
import com.example.myapplication.data.repositories.NetworkState


class PopularMoviePagedListAdapter(val context: Context) : PagedListAdapter<Movie, RecyclerView.ViewHolder>(
    MovieDiffCallback()

) {

    val MOVIE_VIEW_TYPE = 1
    val NETWORK_VIEW_TYPE = 2

    private var networkState: NetworkState? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        if (viewType == MOVIE_VIEW_TYPE) {
            view = layoutInflater.inflate(R.layout.movie_list_item, parent, false)
            return MovieItemViewHolder(view)
        } else {
            view = layoutInflater.inflate(R.layout.network_state_item, parent, false)
            return NetworkStateItemViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == MOVIE_VIEW_TYPE) {
            (holder as MovieItemViewHolder).bind(getItem(position),context)
        }
        else {
            (holder as NetworkStateItemViewHolder).bind(networkState)
        }
    }


    private fun hasExtraRow(): Boolean {
        return networkState != null && networkState != NetworkState.LOADED
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            NETWORK_VIEW_TYPE
        } else {
            MOVIE_VIEW_TYPE
        }
    }




    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.poster_path == newItem.poster_path
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }


    class MovieItemViewHolder (private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(movie: Movie?,context: Context) {
            view.findViewById<TextView>(R.id.cv_movie_title).text = movie?.title
            view.findViewById<TextView>(R.id.cv_movie_release_date).text =  movie?.release_date

            val moviePosterURL = POSTER_BASE_URL + movie?.poster_path
            Glide.with(view.context)
                .load(moviePosterURL)
                .into(view.findViewById<ImageView>(R.id.cv_movie_release_date));

//            itemView.setOnClickListener{
//                val intent = Intent(context, SingleMovie::class.java)
//                intent.putExtra("id", movie?.id)
//                context.startActivity(intent)
//            }

        }

    }

    class NetworkStateItemViewHolder (private val view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("CutPasteId")
        fun bind(networkState: NetworkState?) {
            if (networkState != null && networkState == NetworkState.LOADING) {
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.VISIBLE;
            }
            else  {
                view.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE;
            }

            if (networkState != null && networkState == NetworkState.ERROR) {
                view.findViewById<TextView>(R.id.error_msg_item).visibility  = View.VISIBLE;
                view.findViewById<TextView>(R.id.error_msg_item).text = networkState.msg;
            }
            else if (networkState != null && networkState == NetworkState.ENDOFLIST) {
                view.findViewById<TextView>(R.id.error_msg_item).visibility = View.VISIBLE;
                view.findViewById<TextView>(R.id.error_msg_item).text  = networkState.msg;
            }
            else {
                view.findViewById<TextView>(R.id.error_msg_item).visibility  = View.GONE;
            }
        }
    }


    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {                             //hadExtraRow is true and hasExtraRow false
                notifyItemRemoved(super.getItemCount())    //remove the progressbar at the end
            } else {                                       //hasExtraRow is true and hadExtraRow false
                notifyItemInserted(super.getItemCount())   //add the progressbar at the end
            }
        } else if (hasExtraRow && previousState != newNetworkState) { //hasExtraRow is true and hadExtraRow true and (NetworkState.ERROR or NetworkState.ENDOFLIST)
            notifyItemChanged(itemCount - 1)       //add the network message at the end
        }

    }




}