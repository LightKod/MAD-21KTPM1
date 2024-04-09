package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import android.widget.ToggleButton
import androidx.compose.ui.text.toLowerCase
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.adapters.RecyclerViewMovieAdapter
import com.example.mad_21ktpm1_group11.models.Movie

class MovieSearchFragment : Fragment() {
    private lateinit var backBtn: ImageButton
    private lateinit var menuBtn: ImageButton

    private lateinit var editTextSearchQuery: EditText
    private lateinit var toggleButtonFilter: ToggleButton

    private lateinit var recyclerViewMovieList: RecyclerView
    private lateinit var recyclerViewMovieListAdapter: RecyclerViewMovieAdapter

    private lateinit var movieList: ArrayList<Movie>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_movie_search, container, false)

        init(view)

        return view
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

        editTextSearchQuery = view.findViewById(R.id.editTextSearchQuery)
        toggleButtonFilter = view.findViewById(R.id.toggleButtonFilter)

        recyclerViewMovieList = view.findViewById(R.id.recyclerViewMovieList)

        movieList = ArrayList()

        recyclerViewMovieListAdapter = RecyclerViewMovieAdapter(this, movieList)
        recyclerViewMovieList.adapter = recyclerViewMovieListAdapter
        recyclerViewMovieList.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration: DividerItemDecoration = DividerItemDecoration(recyclerViewMovieList.context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.requireContext(), R.drawable.item_divider)!!)
        recyclerViewMovieList.addItemDecoration(dividerItemDecoration)

        recyclerViewMovieListAdapter.onItemClick = {id ->
            Toast.makeText(this.requireContext(), "Clicked item: $id", Toast.LENGTH_SHORT).show()
        }

        // Search operation
        editTextSearchQuery.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_SEARCH && !editTextSearchQuery.text.isEmpty()){
                val newMovieList: ArrayList<Movie> = ArrayList()
                for(i in 1..10){
                    newMovieList.add(Movie(
                        i,
                        if(i % 2 == 0) "Meo meo $i" else "Woof woof $i",
                        if (i % 5 == 1) "T13" else if (i % 5 == 2) "T16" else if (i % 5 == 3) "T18" else if (i % 5 == 4) "K" else "P",
                        "01/01/2000",
                        125 + i,
                        "Meo meo",
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSMRsQpTd3EBxP_VQLMxiUiP1YtbtPGxGEWhscmTV_tCg&s"
                    ))
                }

                val filteredList = newMovieList.filter { movie ->
                    movie.name.contains(editTextSearchQuery.text.toString() ,ignoreCase = true)
                }

                recyclerViewMovieListAdapter.updateList(filteredList)
                recyclerViewMovieList.scrollToPosition(0)
            }
            true
        }
    }
}