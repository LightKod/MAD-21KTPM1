package com.example.mad_21ktpm1_group11.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mad_21ktpm1_group11.fragments.TicketListViewFragment

class ViewPagerTicketApdater(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = TicketListViewFragment()
        fragment.arguments = Bundle().apply {
            when (position) {
                0 -> putString("type", "unused")
                1 -> putString("type", "used")
                else -> putString("type", "How can you get there dummy?")
            }
        }
        return fragment
    }
}