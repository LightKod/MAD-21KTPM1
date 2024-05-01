package com.example.mad_21ktpm1_group11.models

import android.graphics.Color

data class Seat (
    var name: String,
    var id: Int = 0,
    var status: SeatStatus = SeatStatus.None,
    var defaultStatus: SeatStatus = SeatStatus.None,
    var defaultColor: Int = Color.parseColor("#222222"),
    var price: Double = 2.0,
)
{

    companion object{
        val COLOR_BOOKED = Color.parseColor("#dbd7cd")
        val COLOR_CHOOSING = Color.parseColor("#ad2b33")
        val COLOR_VIP = Color.parseColor("#914456")
        val COLOR_NONE = Color.parseColor("#222222")
        val COLOR_NORMAL = Color.parseColor("#aa9c8f")

        enum class SeatStatus(val value: Int, val color: Int) {
            None(0, COLOR_NONE),
            Normal(1, COLOR_NORMAL),
            VIP(2, COLOR_VIP),
            Booked(3, COLOR_BOOKED),
            Choosing(4, COLOR_CHOOSING);

            companion object {
                fun fromInt(value: Int) = values().first { it.value == value }
            }
        }
    }
}