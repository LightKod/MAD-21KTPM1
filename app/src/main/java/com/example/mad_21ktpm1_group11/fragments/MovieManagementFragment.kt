package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.adapters.RecyclerViewMovieAdapter
import com.example.mad_21ktpm1_group11.models.Movie
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MovieManagementFragment : Fragment() {
    private lateinit var backBtn: ImageButton
    private lateinit var menuBtn: ImageButton

    private lateinit var recyclerViewMovieList: RecyclerView
    private lateinit var recyclerViewMovieListAdapter: RecyclerViewMovieAdapter

    private lateinit var movieList: ArrayList<Movie>

    private lateinit var addMovieBtn: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_management, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init(view)
    }

    private fun init(view: View){
        backBtn = view.findViewById(R.id.backBtn)
        menuBtn = view.findViewById(R.id.menuBtn)

        backBtn.setOnClickListener {
            (this.activity as? MainActivity)?.goBack()
        }

        menuBtn.setOnClickListener {
            (this.activity as? MainActivity)?.openDrawer()
        }

        recyclerViewMovieList = view.findViewById(R.id.recyclerViewMovieList)

        movieList = ArrayList()
        for(i in 1..10){
            movieList.add(Movie(
                i,
                "Movie name $i",
                if (i % 5 == 1) "T13" else if (i % 5 == 2) "T16" else if (i % 5 == 3) "T18" else if (i % 5 == 4) "K" else "P",
                "01/01/2000",
                125 + i,
                "Meo meo",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMRsQpTd3EBxP_VQLMxiUiP1YtbtPGxGEWhscmTV_tCg&s"
            ))
        }

        recyclerViewMovieListAdapter = RecyclerViewMovieAdapter(this, movieList)
        recyclerViewMovieList.adapter = recyclerViewMovieListAdapter
        recyclerViewMovieList.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration: DividerItemDecoration = DividerItemDecoration(recyclerViewMovieList.context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.requireContext(), R.drawable.item_divider)!!)
        recyclerViewMovieList.addItemDecoration(dividerItemDecoration)

        recyclerViewMovieListAdapter.onItemClick = {id ->
            val fragment = MovieManagementDetailFragment()
            fragment.arguments = Bundle().apply {
                putString("type", "edit")
            }
            (this.activity as? MainActivity)?.addFragment(fragment, "movie_management_detail")
        }

        addMovieBtn = view.findViewById(R.id.addMovieBtn)

        addMovieBtn.setOnClickListener {
            val fragment = MovieManagementDetailFragment()
            fragment.arguments = Bundle().apply {
                putString("type", "add")
            }
            (this.activity as? MainActivity)?.addFragment(fragment, "movie_management_detail")
        }
    }
}