package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.adapters.RecyclerViewFoodAdapter
import com.example.mad_21ktpm1_group11.models.Food

class FoodOrderFragment : Fragment() {
    private lateinit var root: View

    private lateinit var recyclerView: RecyclerView

    lateinit var adapter: RecyclerViewFoodAdapter

    private lateinit var foodList: ArrayList<Food>

    val foodBought = hashMapOf<Int, Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_food_order, container, false)
        init()
        return root;
    }

    private fun init()
    {
        recyclerView = root.findViewById(R.id.recyclerView)

        var imgList = ArrayList<String>()
        imgList.add("https://iguov8nhvyobj.vcdn.cloud/media/concession/web/6465deb2716d7_1684397746.png")
        imgList.add("https://iguov8nhvyobj.vcdn.cloud/media/concession/web/651711ff95995_1696010752.png")
        imgList.add("https://iguov8nhvyobj.vcdn.cloud/media/concession/web/6465df9cdef9a_1684397981.png")
        imgList.add("https://iguov8nhvyobj.vcdn.cloud/media/concession/web/660388e83e6d3_1711507688.png")
        imgList.add("https://iguov8nhvyobj.vcdn.cloud/media/concession/web/6603874e7b0db_1711507279.png")

        foodList = ArrayList()

        for (i in 0 until 5)
        {
            foodList.add(Food(i, "My Combo", "Test description", 5.0, imgList[i]))
        }

        adapter = RecyclerViewFoodAdapter(this, foodList)
        recyclerView.adapter = adapter;
        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter.onFoodAdd = { foodId ->
            {
                if(!foodBought.containsKey(foodId)){
                    foodBought[foodId] = 1;
                }else{
                    foodBought[foodId] = foodBought[foodId]!! + 1;
                }
            }
        }

        adapter.onFoodRemove = { foodId ->
            {
                if(foodBought.containsKey(foodId)){
                    foodBought[foodId] = foodBought[foodId]!! - 1;
                }
            }
        }
    }

}