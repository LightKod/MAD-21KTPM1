package com.example.mad_21ktpm1_group11.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_21ktpm1_group11.R

class ButtonTimeAdapter(private val timeList: List<String>, private val onItemClick: (String) -> Unit) : RecyclerView.Adapter<ButtonTimeAdapter.ButtonTimeViewHolder>() {

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
        holder.buttonTime.setOnClickListener { onItemClick(currentTime) }
    }

    override fun getItemCount(): Int {
        return timeList.size
    }
}