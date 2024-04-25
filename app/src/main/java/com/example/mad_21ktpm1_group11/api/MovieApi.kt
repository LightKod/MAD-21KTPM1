package com.example.mad_21ktpm1_group11.api
import com.example.mad_21ktpm1_group11.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movies/currently-showing") // Đường dẫn tới API
    fun getCurrentlyShowingMovies(): Call<List<Movie>>

    @GET("movies/{id}")
    fun getMovieByID(@Path("id") id: Int): Call<Movie>

    @GET("movies")
    fun getAllMovies(): Call<List<Movie>>

    @GET("movies/search")
    fun getMoviesByName(@Query("keyword") keyword: String, @Query("currently_showing") currentlyShowing: Boolean): Call<List<Movie>>

//    @GET("/fact")
//    fun getCatFact(): Call<CatFactResponse>
}