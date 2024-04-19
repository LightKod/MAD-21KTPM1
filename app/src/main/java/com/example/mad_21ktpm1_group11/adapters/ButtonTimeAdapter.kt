package com.example.mad_21ktpm1_group11.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

import androidx.recyclerview.widget.RecyclerView
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.api.MovieApi
import com.example.mad_21ktpm1_group11.api.RetrofitClient
import com.example.mad_21ktpm1_group11.fragments.SeatSelectionFragment
import com.example.mad_21ktpm1_group11.models.Movie
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class ButtonTimeAdapter(private val fragment : Fragment, private val timeList: List<String>, private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<ButtonTimeAdapter.ButtonTimeViewHolder>() {

    inner class ButtonTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buttonTime: Button = itemView.findViewById(R.id.buttonTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonTimeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.time_button_layout, parent, false)
        return ButtonTimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ButtonTimeViewHolder, position: Int) {
        val currentTime = timeList[position]
        holder.buttonTime.text = currentTime
        holder.buttonTime.setOnClickListener { onItemClick(currentTime)
            Log.d("chuyenSangKhac","da chuyen")
//            val movieApi = RetrofitClient.instance.create(MovieApi::class.java)
//            val call = movieApi.getCurrentlyShowingMovies()
//            call.enqueue(object : Callback<List<Movie>> {
//                override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
//                    Log.d("MovieData","aaa")
//
//                    if (response.isSuccessful) {
//                        val movies = response.body()
//                        Log.d("MovieData","aaa")
//                        if (movies != null) {
//                            for (movie in movies) {
//                                Log.d("MovieData", "Movie title: ${movie.id}")
//                                // Log thêm các thuộc tính khác của movie nếu cần
//                            }
//                        } else {
//                            Log.e("MovieData", "Response body is null")
//                        }
//                        // Xử lý danh sách phim ở đây
//                    } else {
//                        Log.d("MovieData","bbbb")
//
//                        // Xử lý khi có lỗi từ server
//                    }
//                }
//
//                override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
//                    Log.e("NetworkError", "Error occurred while connecting to server: ${t.message}")
//                    // Xử lý khi gặp lỗi kết nối
//                }
//
//            })

            (fragment.activity as? MainActivity)?.addFragment(SeatSelectionFragment(),"seatSelection")
        }
    }

    override fun getItemCount(): Int {
        return timeList.size
    }
}