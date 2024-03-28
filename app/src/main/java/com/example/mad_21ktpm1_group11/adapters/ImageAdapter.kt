package com.example.mad_21ktpm1_group11.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mad_21ktpm1_group11.R

class ImageAdapter(private val fragment : Fragment, private val imageList : ArrayList<Int>)
    : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>(){

    lateinit var onItemClick: ((Int) -> Unit)

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val imageView: ImageView = itemView.findViewById(R.id.imageView)

        init{
            itemView.setOnClickListener {
                onItemClick.invoke(imageList[position])
            }
        }
    }

    init{
        imageList.add(0, imageList.last())
        imageList.add(imageList[1])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item_container, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Glide.with(fragment).load(imageList[position]).into(holder.imageView)
    }
}