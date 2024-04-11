package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R

class MovieDetailFragment : Fragment() {
    var btnBook : Button ?= null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_detail, container, false)
        btnBook = view.findViewById(R.id.buttonBook)
        btnBook!!.setOnClickListener {
            (this.activity as? MainActivity)?.addFragment(BookingTimeFragment(),"bookingTime")
        }
        return  view
    }
}