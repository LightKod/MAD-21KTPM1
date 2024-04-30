package com.example.mad_21ktpm1_group11.models

import android.graphics.Color

data class Seat (
    var name: String,
    var status: SeatStatus = SeatStatus.None,
    val defaultStatus: SeatStatus = SeatStatus.None,
    var defaultColor: Int = Color.parseColor("#222222"),
    var price: Double = 2.0,
)
{

    companion object{
        public enum class SeatStatus (val value: Int){
            None(0),
            Normal(1),
            VIP(2),
            Booked(3),
            Choosing(4),
        }
    }
}