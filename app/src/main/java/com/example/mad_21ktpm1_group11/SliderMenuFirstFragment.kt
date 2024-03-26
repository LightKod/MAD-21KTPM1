package com.example.mad_21ktpm1_group11

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class SliderMenuFirstFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_slider_menu_first, container, false)

        view.findViewById<Button>(R.id.memberBtn).setOnClickListener {
            (this.activity as? MainActivity)?.addFragment(UserDashboardFragment(), "member")
        }

        return view
    }
}