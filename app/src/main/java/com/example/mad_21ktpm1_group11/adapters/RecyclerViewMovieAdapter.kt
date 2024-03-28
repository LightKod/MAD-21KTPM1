package com.example.mad_21ktpm1_group11.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.models.Movie

class RecyclerViewMovieAdapter(private val fragment : Fragment, private val movies: List<Movie>): RecyclerView.Adapter<RecyclerViewMovieAdapter.ViewHolder>() {
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val moviePoster = view.findViewById<ImageView>(R.id.moviePoster)
        val textViewMovieName = view.findViewById<TextView>(R.id.textViewMovieName)
        val textViewMovieDirector = view.findViewById<TextView>(R.id.textViewMovieDirector)
        val textViewMoviePremiereDate = view.findViewById<TextView>(R.id.textViewMoviePremiereDate)
        val textViewMovieDuration = view.findViewById<TextView>(R.id.textViewMovieDuration)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.recyclerview_movie_item_container, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Movie = movies[position]
        holder.textViewMovieName.text = item.name
        holder.textViewMovieDirector.text = item.director
        holder.textViewMoviePremiereDate.text = item.premiereDate
        holder.textViewMovieDuration.text = item.duration.toString()
        Glide.with(fragment).load(item.poster).into(holder.moviePoster)
    }
}