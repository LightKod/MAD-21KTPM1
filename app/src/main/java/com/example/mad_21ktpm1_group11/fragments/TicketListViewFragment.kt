package com.example.mad_21ktpm1_group11.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_21ktpm1_group11.MainActivity
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.adapters.RecyclerViewTicketAdapter
import com.example.mad_21ktpm1_group11.models.Ticket

class TicketListViewFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView

    private lateinit var ticketList: ArrayList<Ticket>

    private lateinit var recyclerViewAdapter: RecyclerViewTicketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ticket_list_view, container, false)

        recyclerView = view.findViewById(R.id.recyclerViewTicketList)

        ticketList = ArrayList()
        arguments?.takeIf { it.containsKey("type") }?.apply {
            if(getString("type") == "unused"){
                for(i in 1..5){
                    ticketList.add(Ticket(
                        10000 + i,
                        if(i % 2 == 0) "Short movie's name" else "Super mega ultra ultimate final movie's name pro max 15",
                        "Jan 01, 2000",
                        "CGV Su Van Hanh",
                        "200000"
                    ))
                }
            }
            else{
                for(i in 1..10){
                    ticketList.add(Ticket(
                        10000 + i,
                        if(i % 2 == 0) "Ehe you saw me" else "I'm afraid I won't be here for long, so when I go, please forget everything about me ok?",
                        "Jan 01, 2000",
                        "CGV Su Van Hanh",
                        "300000"
                    ))
                }
            }
        }

        recyclerViewAdapter = RecyclerViewTicketAdapter(this, ticketList)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false)
        val dividerItemDecoration: DividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(this.requireContext(), R.drawable.item_divider)!!)
        recyclerView.addItemDecoration(dividerItemDecoration)

        recyclerViewAdapter.onItemClick = {id ->
            Toast.makeText(this.requireContext(), "Clicked item: $id", Toast.LENGTH_SHORT).show()
            (this.activity as? MainActivity)?.addFragment(TicketDetailFragment(), "ticket_detail")
        }

        return view
    }
}