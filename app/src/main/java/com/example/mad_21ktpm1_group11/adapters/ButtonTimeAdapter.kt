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
import com.example.mad_21ktpm1_group11.fragments.SeatSelectionFragment

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
            (fragment.activity as? MainActivity)?.addFragment(SeatSelectionFragment(),"seatSelection")
        }
    }

    override fun getItemCount(): Int {
        return timeList.size
    }
}