package com.example.mad_21ktpm1_group11.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
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
        val movieClassification = view.findViewById<Button>(R.id.movieClassification)
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
        val durationString = item.duration.toString() + " min"
        holder.textViewMovieDuration.text = durationString
        Glide.with(fragment).load(item.poster).into(holder.moviePoster)

        when(item.classification){
            "P" -> {
                holder.movieClassification.text = "P"
                holder.movieClassification.setTextColor(Color.parseColor("#799D46"))
                holder.movieClassification.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#799D46"))
            }
            "K" -> {
                holder.movieClassification.text = "K"
                holder.movieClassification.setTextColor(Color.parseColor("#1A9ABD"))
                holder.movieClassification.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#1A9ABD"))
            }
            "T13" -> {
                holder.movieClassification.text = "T13"
                holder.movieClassification.setTextColor(Color.parseColor("#E8E10A"))
                holder.movieClassification.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#E8E10A"))
            }
            "T16" -> {
                holder.movieClassification.text = "T16"
                holder.movieClassification.setTextColor(Color.parseColor("#F3A001"))
                holder.movieClassification.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#F3A001"))
            }
            "T18" -> {
                holder.movieClassification.text = "T18"
                holder.movieClassification.setTextColor(Color.parseColor("#EA3B24"))
                holder.movieClassification.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#EA3B24"))
            }
            else -> {
                holder.movieClassification.text = "U"
                holder.movieClassification.setTextColor(Color.parseColor("#000000"))
                holder.movieClassification.backgroundTintList = ColorStateList.valueOf(Color.parseColor("#000000"))
            }
        }
    }
}