package com.example.mad_21ktpm1_group11.fragments

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.mad_21ktpm1_group11.R

class LoginFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null




    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        val forgotPasswordTextView = view.findViewById<TextView>(R.id.forgotPasswordTextView)
        forgotPasswordTextView.paintFlags = forgotPasswordTextView.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        return view
    }



}