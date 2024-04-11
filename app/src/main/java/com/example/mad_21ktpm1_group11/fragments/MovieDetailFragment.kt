package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.models.Review

class MovieDetailFragment : Fragment() {
    private lateinit var root: View;

    private lateinit var btnBook : Button
    private lateinit var btnReview: AppCompatButton


    private lateinit var videoTrailer: VideoView
    private lateinit var imagePoster: ImageView

    private lateinit var textMovieName: TextView
    private lateinit var textDate: TextView
    private lateinit var textTime: TextView
    private lateinit var textDescription: TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        init()
        return  root
    }

    private fun init()
    {
        btnBook = root.findViewById(R.id.buttonBook)
        btnReview = root.findViewById(R.id.buttonReview)

        videoTrailer = root.findViewById(R.id.videoTrailer)
        imagePoster = root.findViewById(R.id.imagePoster)

        textMovieName = root.findViewById(R.id.textMovieName)
        textDate = root.findViewById(R.id.textDate)
        textTime = root.findViewById(R.id.textTime)
        textDescription = root.findViewById(R.id.textDescription)

        Glide.with(this).load("https://iguov8nhvyobj.vcdn.cloud/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/4/7/470x700-kungfupanda4.jpg").into(imagePoster)

        btnBook.setOnClickListener {
            (this.activity as? MainActivity)?.addFragment(BookingTimeFragment(),"bookingTime")
        }

        btnReview.setOnClickListener{
            (this.activity as? MainActivity)?.addFragment(MovieReviewFragment(), "reviewList")
        }


    }

}