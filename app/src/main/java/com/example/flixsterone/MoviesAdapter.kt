package com.example.flixsterone
import android.content.ContentValues.TAG
import android.util.Log
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MoviesAdapter(
    private val movies: List<Movie>,
    private val mListener: OnListFragmentInteractionListene?)
    : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> ()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }
    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(R.id.movie_title) as TextView
        val mMovieOverview: TextView = mView.findViewById<View>(R.id.movie_director) as TextView
        val mMovieImage: ImageView = mView.findViewById<View>(R.id.movie_image) as ImageView

        override fun toString(): String {
            return mMovieTitle.toString() + " '" + mMovieOverview.text + "'"
        }
    }
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val m = movies[position]

        holder.mItem = m
        holder.mMovieTitle.text = m.title
        holder.mMovieOverview.text = m.overview
        Glide.with(holder.mView)

            .load("https://image.tmdb.org/t/p/w500/"+m.poster_path.toString())
            .centerInside()
            .into(holder.mMovieImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }






        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

}