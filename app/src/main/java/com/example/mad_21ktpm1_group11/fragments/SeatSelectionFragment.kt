package com.example.mad_21ktpm1_group11.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.classes.ZoomLayout


class SeatSelectionFragment : Fragment(), View.OnTouchListener {
    private lateinit var mainLayout: RelativeLayout //The seats is put here to navigate
    private lateinit var root: View
    private lateinit var zoomLayout: ZoomLayout

    private var mainID: Int = 0;


    private var _xDelta = 0
    private var _yDelta = 0

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
    }

    private fun SetupView()
    {
        mainLayout = RelativeLayout(zoomLayout.context);

        val layoutParams = RelativeLayout.LayoutParams(3000, 5000)

        var extraWidth = 2000
        var extraHeight = 1200
        val displayMetrics = resources.displayMetrics
        layoutParams.height = displayMetrics.heightPixels + extraHeight;
        layoutParams.width = displayMetrics.widthPixels + extraWidth;

        layoutParams.setMargins(-(extraWidth /2),-(extraHeight /2),-(extraWidth /2),-(extraHeight /2))


        mainID = View.generateViewId()
        mainLayout.id = mainID

        mainLayout.setBackgroundColor(Color.parseColor("#000000"))

        mainLayout.setLayoutParams(layoutParams);

        //mainLayout.setOnTouchListener(this)
        zoomLayout.setOnTouchListener(this)

        zoomLayout.addView(mainLayout)
    }

    fun PopulateView()
    {
        var testButton = Button(mainLayout.context)

        val layoutParams = RelativeLayout.LayoutParams(100, 100)
        layoutParams.setMargins(50,100,-250,-250)
        testButton.setLayoutParams(layoutParams)

        testButton.setBackgroundColor(Color.parseColor("#914456"))
        testButton.setOnTouchListener(this)
        mainLayout.addView(testButton)
    }


    override fun onTouch(view: View?, event: MotionEvent?): Boolean
    {
        zoomLayout.init(this.context);
        return false;
    }
}