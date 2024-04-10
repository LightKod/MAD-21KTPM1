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
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.models.Seat
import com.example.mad_21ktpm1_group11.views.SeatButton
import com.example.mad_21ktpm1_group11.views.ZoomLayout


class SeatSelectionFragment : Fragment(), View.OnTouchListener {
    private lateinit var mainLayout: RelativeLayout //The seats is put here to navigate
    private lateinit var outerLayout: RelativeLayout
    private lateinit var root: View
    private lateinit var zoomLayout: ZoomLayout

    private lateinit var textMovieName: TextView;
    private lateinit var textScreenType: TextView;
    private lateinit var textPrice: TextView;
    private lateinit var textSeatSelected: TextView;

    private lateinit var buttonPay: Button;

    private var mainID: Int = 0;

    private var columns = 20
    private var rows = 10

    private val SEAT_SIZE = 125;
    private val SEAT_PADDING = 5;

    private val PADDING_HOR = 300;
    private val PADDING_VER = 500;

    private lateinit var seats: Array<Array<Seat>>

    val COLOR_BOOKED = Color.parseColor("#dbd7cd")
    val COLOR_CHOOSING = Color.parseColor("#ad2b33")
    val COLOR_VIP  = Color.parseColor("#914456")
    val COLOR_NONE  = Color.parseColor("#222222")
    val COLOR_NORMAL = Color.parseColor("#aa9c8f")

    val chosenSeats:  MutableCollection<Seat> = mutableListOf();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_seat_selection, container, false)

        init();
        //TODO: add a step to fetch data here
        setupView()
        populateView()

        updateOrderDetails();
        return root;
    }

    private fun init()
    {
        zoomLayout = root.findViewById(R.id.zoomLayout);
        outerLayout = root.findViewById(R.id.outerLayout);

        textMovieName = root.findViewById(R.id.textMovieName)
        textScreenType = root.findViewById(R.id.textScreenType)
        textSeatSelected = root.findViewById(R.id.textSeatSelected)
        textPrice = root.findViewById(R.id.textPrice)

        buttonPay = root.findViewById(R.id.buttonPay)

        buttonPay.setOnClickListener{
            (this.activity as? MainActivity)?.addFragment(FoodOrderFragment(), "food")
        }
    }

    private fun updateOrderDetails(){


        textPrice.text = "$${chosenSeats.sumByDouble{it.price}}";
        textSeatSelected.text = "${chosenSeats.count()} Seats Selected";
    }


    private fun setupView()
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

    fun populateView()
    {
        val PADDING_TOP = PADDING_HOR

        //TODO: Get this from the DATABASE
        seats = Array(rows) { row ->
            Array(columns) { column ->
                Seat("A0" ,Seat.Companion.SeatStatus.Normal,Seat.Companion.SeatStatus.Normal,COLOR_NORMAL) // Initialize each seat with the None status
            }
        }

        for (y in 0 until rows){
            for (x in 0 until columns)
            {
                val seatName = mapToAlphabet(y)+x.toString();
                seats[y][x].name = seatName;
            }
        }


        for (y in 0 until rows){
            for (x in 0 until columns){

                var seatButton = SeatButton(mainLayout.context)

                val layoutParams = RelativeLayout.LayoutParams(SEAT_SIZE, SEAT_SIZE)
                layoutParams.setMargins(x * (SEAT_SIZE + SEAT_PADDING) + PADDING_VER,y * (SEAT_SIZE + SEAT_PADDING) + PADDING_TOP,0,0)
                seatButton.setLayoutParams(layoutParams)

                var seatData =  seats[y][x]

                seatButton.text = seatData.name;
                seatButton.setTextColor(Color.parseColor("#FFFFFF"))

                seatButton.setBackgroundColor(seatData.defaultColor)


                seatButton.setOnClickListener {
                    when(seatData.status){
                        Seat.Companion.SeatStatus.None -> {

                        }
                        Seat.Companion.SeatStatus.Choosing -> {
                            seatButton.setBackgroundColor(seatData.defaultColor)
                            seatData.status = seatData.defaultStatus
                            chosenSeats.remove(seatData);

                        }
                        else -> {
                            seatButton.setBackgroundColor(COLOR_CHOOSING)
                            seatData.status = Seat.Companion.SeatStatus.Choosing
                            chosenSeats.add(seatData);
                        }

                    }
                    updateOrderDetails()
                }
                mainLayout.addView(seatButton)
            }
        }
    }

    fun mapToAlphabet(n: Int): Char {
        require(n >= 0) { "Input value must be non-negative" }
        return (n + 65).toChar()
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