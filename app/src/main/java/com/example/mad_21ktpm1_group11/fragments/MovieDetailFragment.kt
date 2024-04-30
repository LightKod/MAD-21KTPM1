package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import android.util.Log
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
import com.example.mad_21ktpm1_group11.api.AuthService
import com.example.mad_21ktpm1_group11.api.MovieApi
import com.example.mad_21ktpm1_group11.api.RetrofitClient
import com.example.mad_21ktpm1_group11.models.AuthResponse
import com.example.mad_21ktpm1_group11.models.Movie
import com.example.mad_21ktpm1_group11.models.Person
import com.example.mad_21ktpm1_group11.models.Review
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailFragment : Fragment() {
    private lateinit var root: View;

    private lateinit var movie: Movie

    private lateinit var btnBook : Button
    private lateinit var btnReview: AppCompatButton


    private lateinit var imageBackdrop: ImageView
    private lateinit var imagePoster: ImageView

    private lateinit var textMovieName: TextView
    private lateinit var textDate: TextView
    private lateinit var textTime: TextView
    private lateinit var textDescription: TextView
    private lateinit var textCast: TextView
    private lateinit var textDirector: TextView

    private var movieID = 438631
    private lateinit var people: List<Person>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_movie_detail, container, false)

        arguments?.takeIf { it.containsKey("id") }?.apply {
            movieID = getInt("id")
        }

        fetchData()
        init()
        return  root
    }

    private fun fetchData()
    {
        val movieService = RetrofitClient.instance.create(MovieApi::class.java)
        val call = movieService.getMovieByID(movieID)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    // Handle successful response
                    movie = response.body()!!
                    Log.i("API", movie.name)
                    fetchPeopleData()
                } else {
                    val errorMessage = response.message()
                    Log.i("API", errorMessage)
                    Log.i("API", "GET FAILED")
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.i("API", t.message!!)
            }
        })
    }

    private fun fetchPeopleData(){
        val movieService = RetrofitClient.instance.create(MovieApi::class.java)
        val call = movieService.getAllPersonInvolved(movie.id)
        call.enqueue(object : Callback<List<Person>> {
            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
                if (response.isSuccessful) {
                    people = response.body()!!
                    setValue()
                } else {
                    val errorMessage = response.message()
                    Log.i("API", errorMessage)
                    Log.i("API", "GET FAILED")
                }
            }

            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
                Log.i("API", t.message!!)
            }
        })
    }

    private fun init()
    {
        btnBook = root.findViewById(R.id.buttonBook)
        btnReview = root.findViewById(R.id.buttonReview)

        imageBackdrop = root.findViewById(R.id.imageBackdrop)
        imagePoster = root.findViewById(R.id.imagePoster)

        textMovieName = root.findViewById(R.id.textMovieName)
        textDate = root.findViewById(R.id.textDate)
        textTime = root.findViewById(R.id.textTime)
        textDescription = root.findViewById(R.id.textDescription)
        textCast = root.findViewById(R.id.textCast)
        textDirector = root.findViewById(R.id.textDirector)

        btnBook.setOnClickListener {
            (this.activity as? MainActivity)?.addFragment(BookingTimeFragment(),"bookingTime")
        }

        btnReview.setOnClickListener{
            (this.activity as? MainActivity)?.addFragment(MovieReviewFragment(), "reviewList")
        }
    }

    private fun setValue()
    {
        val castlist = people.subList(1, people.size)
        val namesString = castlist.joinToString(separator = ", ") { it.name }

        textMovieName.text = movie.name;
        textDate.text = extractDate(movie.premiereDate);
        textTime.text = formatTime((movie.duration))
        textDirector.text = people[0].name
        textCast.text = namesString

        Glide.with(this).load( "https://image.tmdb.org/t/p/original" + movie.poster).into(imagePoster)
        Glide.with(this).load( "https://image.tmdb.org/t/p/original" + movie.backdropPath).into(imageBackdrop)
    }


    private fun formatTime(minutes: Int): String {
        val hours = minutes / 60
        val remainingMinutes = minutes % 60

        return if (hours == 0) {
            "$remainingMinutes mins"
        } else if (remainingMinutes == 0) {
            "$hours hrs"
        } else {
            "$hours hrs $remainingMinutes mins"
        }
    }
    private fun extractDate(dateTimeString: String): String {
        // Split the string using "T" as the delimiter and get the first part
        val datePart = dateTimeString.split("T")[0]
        return datePart
    }
}