package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.adapters.RecyclerViewFoodAdapter
import com.example.mad_21ktpm1_group11.api.FoodApi
import com.example.mad_21ktpm1_group11.api.OrderApi
import com.example.mad_21ktpm1_group11.api.RetrofitClient
import com.example.mad_21ktpm1_group11.api.ScheduleApi
import com.example.mad_21ktpm1_group11.models.Food
import com.example.mad_21ktpm1_group11.models.Movie
import com.example.mad_21ktpm1_group11.models.Order
import com.example.mad_21ktpm1_group11.models.Schedule
import com.google.gson.Gson
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale
import java.util.UUID
import kotlin.random.Random

class FoodOrderFragment : Fragment() {
    private lateinit var root: View

    private lateinit var recyclerView: RecyclerView

    lateinit var adapter: RecyclerViewFoodAdapter

    private lateinit var foodList: ArrayList<Food>

    private val foodBought = hashMapOf<Int, Int>()

    private var currentPrice: Double = 0.0;
    private var selectedSeatID: String = "";
    private lateinit var schedule: Schedule;
    private var movieName = "";

    private lateinit var textMovieName: TextView;
    private lateinit var textScreenType: TextView;
    private lateinit var textPrice: TextView;
    private lateinit var textSeatSelected: TextView;

    private lateinit var buttonPay: Button;

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_food_order, container, false)

        arguments?.takeIf { it.containsKey("schedule") }?.apply {
            schedule = getSerializable("schedule") as Schedule
        }
        arguments?.takeIf { it.containsKey("selectedSeatID") }?.apply {
            selectedSeatID = getString("selectedSeatID")!!
        }
        arguments?.takeIf { it.containsKey("currentPrice") }?.apply {
            currentPrice = getDouble("currentPrice")
        }
        arguments?.takeIf { it.containsKey("movieName") }?.apply {
            movieName = getString("movieName")!!
        }

        fetchData()
        return root;
    }

    private fun init()
    {
        recyclerView = root.findViewById(R.id.recyclerView)

        adapter = RecyclerViewFoodAdapter(this, foodList)
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter.onFoodAdd = { foodId ->
            if (!foodBought.containsKey(foodId)) {
                foodBought[foodId] = 1
            } else {
                foodBought[foodId] = foodBought[foodId]!! + 1
            }
            updateOrderDetails()
        }

        adapter.onFoodRemove = { foodId ->
            if (foodBought.containsKey(foodId)) {
                foodBought[foodId] = foodBought[foodId]!! - 1
            }
            updateOrderDetails()
        }

        textMovieName = root.findViewById(R.id.textMovieName)
        textScreenType = root.findViewById(R.id.textScreenType)
        textSeatSelected = root.findViewById(R.id.textSeatSelected)
        textPrice = root.findViewById(R.id.textPrice)

        buttonPay = root.findViewById(R.id.buttonPay)

        buttonPay.setOnClickListener{
            SubmitData()
        }
    }

    private fun SubmitData()
    {
        val order = Order(
            orderId = Random.nextInt(Int.MAX_VALUE),
            tickets = stringToIntList(selectedSeatID),
            food = getFoodIds(),
            scheduleId = schedule.scheduleId,
            time = System.currentTimeMillis(),
            discount = 0.0,
            totalTicket = currentPrice,
            totalFood = getTotalPrice(),
            status = "Pending",
            paymentMethod = "VNPay",
            total = currentPrice + getTotalPrice(),
        )
        val fragment = PaymentPreviewFragment()
        val gson = Gson()
        val jsonOrder = gson.toJson(order) //val orderFromJson = gson.fromJson(jsonOrder, Order::class.java)
        val args = Bundle()
        args.putString("order", jsonOrder)
        args.putInt("movieId",schedule.movieId)
        args.putInt("cinamaId",schedule.cinemaId)
        args.putString("roomId",schedule.roomId.toString())
        Log.i("movieId",schedule.movieId.toString())
        fragment.arguments = args;

        val orderApi = RetrofitClient.instance.create(OrderApi::class.java)

        val call = orderApi.uploadOrder(order)
        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                Log.i("API", response.toString())
                switchFragment(fragment)
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.i("API", "Upload failed")
            }
        })
    }

    private fun switchFragment(fragment: Fragment){
        (this.activity as? MainActivity)?.addFragment(fragment, "payment-review")
    }

    private fun fetchData()
    {
        val foodApi = RetrofitClient.instance.create(FoodApi::class.java)
        val call = foodApi.getAllFood()
        call.enqueue(object : Callback<List<Food>> {
            override fun onResponse(call: Call<List<Food>>, response: Response<List<Food>>) {
                if (response.isSuccessful) {
                    foodList = ArrayList<Food>(response.body()!!)
                    Log.i("API", foodList.count().toString())
                    init();
                    updateOrderDetails();
                } else {
                    val errorMessage = response.message()
                    Log.i("API", errorMessage)
                    Log.i("API", "GET FAILED")
                }
            }

            override fun onFailure(call: Call<List<Food>>, t: Throwable) {
                Log.i("API", t.message!!)
            }
        })
    }

    private fun updateOrderDetails(){
        Log.i("API", (currentPrice + getTotalPrice()).toString())
        textMovieName.text = movieName
        textPrice.text = formatToVND(currentPrice + getTotalPrice());
        textSeatSelected.text = "${stringToIntList(selectedSeatID).count()} Seats Selected";
    }

    fun getTotalPrice(): Double {
        var totalPrice = 0.0
        for ((foodId, count) in foodBought) {
            val food = foodList.find { it.id == foodId }
            if (food != null) {
                totalPrice += food.price * count
            }
        }
        return totalPrice
    }
    fun formatToVND(amount: Double): String {
        val formatter = NumberFormat.getCurrencyInstance(Locale("vi", "VN"))
        formatter.currency = Currency.getInstance("VND")
        return formatter.format(amount)
    }

    fun getFoodIds(): List<Int> {
        val foodIds = mutableListOf<Int>()
        for ((foodId, count) in foodBought) {
            repeat(count) {
                foodIds.add(foodId)
            }
        }
        return foodIds
    }

    fun stringToIntList(idsString: String): List<Int> {
        return idsString.split("|").map { it.toInt() }
    }

}