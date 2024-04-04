package com.example.mad_21ktpm1_group11.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_21ktpm1_group11.R
import com.example.mad_21ktpm1_group11.models.Ticket

class RecyclerViewTicketAdapter(private val fragment: Fragment, private val tickets: List<Ticket>): RecyclerView.Adapter<RecyclerViewTicketAdapter.ViewHolder>() {
    lateinit var onItemClick: ((Int) -> Unit)
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val textViewMovieName = view.findViewById<TextView>(R.id.textViewMovieName)
        val textViewTicketDate = view.findViewById<TextView>(R.id.textViewTicketDate)
        val textViewCinemaName = view.findViewById<TextView>(R.id.textViewCinemaName)
        val textViewTicketID = view.findViewById<TextView>(R.id.textViewTicketID)
        val textViewTicketPrice = view.findViewById<TextView>(R.id.textViewTicketPrice)

        init{
            view.setOnClickListener {
                onItemClick.invoke(tickets[adapterPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)

        val view = inflater.inflate(R.layout.recyclerview_ticket_item_container, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tickets.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Ticket = tickets[position]
        holder.textViewMovieName.text = item.movieName
        holder.textViewTicketDate.text = item.bookingDate
        holder.textViewCinemaName.text = item.cinemaName
        holder.textViewTicketID.text = item.id.toString()
        val formattedPrice = item.price + " đ"
        holder.textViewTicketPrice.text = formattedPrice
    }

}