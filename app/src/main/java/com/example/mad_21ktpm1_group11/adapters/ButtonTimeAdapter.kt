package com.example.mad_21ktpm1_group11.adapters

import android.content.Context
import android.os.Bundle
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
import com.example.mad_21ktpm1_group11.models.Schedule
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class ButtonTimeAdapter(private val fragment : Fragment, private val timeList: List<Schedule>, private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<ButtonTimeAdapter.ButtonTimeViewHolder>() {

    inner class ButtonTimeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val buttonTime: Button = itemView.findViewById(R.id.buttonTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonTimeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.time_button_layout, parent, false)
        return ButtonTimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ButtonTimeViewHolder, position: Int) {
        val currentTime = timeList[position].scheduleStart
        holder.buttonTime.text = currentTime
        holder.buttonTime.setOnClickListener { onItemClick(currentTime)
            Log.d("chuyenSangKhac","da chuyen" + timeList[position])
            val args = Bundle()
            args.putSerializable("schedule", timeList[position])
            Log.d("chuyenSangKhac",timeList[position].toString())

            val fragmentSeatSelection = SeatSelectionFragment()
            fragmentSeatSelection.arguments = args
            (fragment.activity as? MainActivity)?.addFragment(fragmentSeatSelection,"seatSelection")
        }
    }

    override fun getItemCount(): Int {
        return timeList.size
    }
}