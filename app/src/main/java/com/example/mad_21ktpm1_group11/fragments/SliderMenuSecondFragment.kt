package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R

class SliderMenuSecondFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_slider_menu_second, container, false)

        view.findViewById<Button>(R.id.newsBtn).setOnClickListener {
            (this.activity as? MainActivity)?.addFragment(NewsAndPromosFragment(), "news")
        }

        return view
    }
}