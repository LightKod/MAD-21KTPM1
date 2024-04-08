package com.example.mad_21ktpm1_group11.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.models.Food
import com.example.mad_21ktpm1_group11.models.Movie

class RecyclerViewFoodAdapter(private val fragment : Fragment, private val foods: List<Food>): RecyclerView.Adapter<RecyclerViewFoodAdapter.ViewHolder>()
{
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val imagePoster = view.findViewById<ImageView>(R.id.imagePoster);
        val textFoodName = view.findViewById<TextView>(R.id.textFoodName);
        val textFoodDescription = view.findViewById<TextView>(R.id.textFoodDescription);
        val buttonAdd = view.findViewById<Button>(R.id.btnAdd);
        val buttonMinus = view.findViewById<Button>(R.id.btnMinus);

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewFoodAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.recyclerview_food_item_container, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return foods.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Food = foods[position]

        holder.textFoodName.text ="${item.name} - $${item.price}" ;
        holder.textFoodDescription.text = item.description;
        Glide.with(fragment).load(item.poster).into(holder.imagePoster)

        Log.i("View", "Checked");
    }
}