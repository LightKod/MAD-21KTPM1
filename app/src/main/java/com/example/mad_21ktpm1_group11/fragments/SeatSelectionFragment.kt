package com.example.mad_21ktpm1_group11.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.classes.SeatButton
import com.example.mad_21ktpm1_group11.classes.ZoomLayout


class SeatSelectionFragment : Fragment(), View.OnTouchListener {
    private lateinit var mainLayout: RelativeLayout //The seats is put here to navigate
    private lateinit var outerLayout: RelativeLayout
    private lateinit var root: View
    private lateinit var zoomLayout: ZoomLayout

    private var mainID: Int = 0;

    private var columns = 20
    private var rows = 10

    private val SEAT_SIZE = 125;
    private val SEAT_PADDING = 5;

    private val PADDING_HOR = 300;
    private val PADDING_VER = 500;


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_seat_selection, container, false)

        init();
        SetupView()
        PopulateView()
        return root;
    }

    private fun init()
    {
        zoomLayout = root.findViewById(R.id.zoomLayout);
        outerLayout = root.findViewById(R.id.outerLayout);
    }


    private fun SetupView()
    {
        var width = PADDING_VER * 2 + (SEAT_SIZE + SEAT_PADDING) * columns
        var height = PADDING_HOR * 2 + (SEAT_SIZE + SEAT_PADDING) * (1 + rows)

        Log.i("ZoomLayout", width.toString())

        zoomLayout.SetSize(width, height)
        mainLayout = RelativeLayout(zoomLayout.context);
        val displayMetrics = resources.displayMetrics
        val layoutParams = RelativeLayout.LayoutParams(width, height)

        var extraWidth = width - displayMetrics.widthPixels;
        var extraHeight = height - displayMetrics.heightPixels;

        layoutParams.setMargins(-(extraWidth /2), 0,-(extraWidth /2), -(extraHeight))

        mainID = View.generateViewId()
        mainLayout.id = mainID
        //mainLayout.setBackgroundColor(Color.parseColor("#000000"))
        mainLayout.layoutParams = layoutParams;

        zoomLayout.setOnTouchListener(this)
        zoomLayout.addView(mainLayout)

        //Screen Text
        var txtScreen = TextView(mainLayout.context)
        val txtScreenLayoutParams = RelativeLayout.LayoutParams(500, 100)
        txtScreenLayoutParams.setMargins(width / 2 - 250, 100, -(width / 2 - 250), 0)
        txtScreen.layoutParams = txtScreenLayoutParams
        txtScreen.text = "SCREEN"
        txtScreen.textSize = 26.0f
        txtScreen.gravity = Gravity.CENTER_HORIZONTAL or Gravity.CENTER_VERTICAL

        mainLayout.addView(txtScreen)
    }

    fun PopulateView()
    {
        val PADDING_TOP = PADDING_HOR

        for (y in 0 until rows){
            for (x in 0 until columns){
                var seatButton = SeatButton(mainLayout.context)

                val layoutParams = RelativeLayout.LayoutParams(SEAT_SIZE, SEAT_SIZE)
                layoutParams.setMargins(x * (SEAT_SIZE + SEAT_PADDING) + PADDING_VER,y * (SEAT_SIZE + SEAT_PADDING) + PADDING_TOP,0,0)
                seatButton.setLayoutParams(layoutParams)

                seatButton.setBackgroundColor(Color.parseColor("#914456"))

                seatButton.setOnClickListener {
                    // Change background color to #ad2b33
                    seatButton.setBackgroundColor(Color.parseColor("#ad2b33"))
                }


                seatButton.isFocusable = false
                mainLayout.addView(seatButton)
            }
        }
    }

    fun CreateSeatArray(width: Int, height: Int): Array<Array<Int>> {
        return Array(height) { Array(width) { 0 } }
    }

    override fun onTouch(view: View?, event: MotionEvent?): Boolean
    {
        zoomLayout.init(this.context);
        return false;
    }
}