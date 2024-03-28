package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R

class UserDashboardFragment : Fragment() {
    private lateinit var backBtn: ImageButton
    private lateinit var menuBtn: ImageButton

    private lateinit var accountDetailBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_dashboard, container, false)

        init(view)

        return view
    }

    fun init(view: View){
        backBtn = view.findViewById(R.id.backBtn)
        menuBtn = view.findViewById(R.id.menuBtn)

        backBtn.setOnClickListener {
            (this.activity as? MainActivity)?.goBack()
        }

        menuBtn.setOnClickListener {
            (this.activity as? MainActivity)?.openDrawer()
        }

        accountDetailBtn = view.findViewById(R.id.accountDetailBtn)

        accountDetailBtn.setOnClickListener {
            (this.activity as? MainActivity)?.addFragment(UserDetailFragment(), "user_detail")
        }
    }
}