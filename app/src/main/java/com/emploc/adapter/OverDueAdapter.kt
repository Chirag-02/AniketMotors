package com.emploc.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emploc.R
import com.emploc.model.Response
import kotlinx.android.synthetic.main.overdue_task_adapter_item.view.*


class OverDueAdapter(
    var response: List<Response>,
    val context: Context,
    val itemCLickLister: ItemCLickLister
) :
    RecyclerView.Adapter<OverDueAdapter.ViewHolder>() {

    var rowIndex = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.overdue_task_adapter_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return response.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val response: Response = response[position]
        holder.locationOverdue.text = response.name


        holder.cardView.setOnClickListener {
//            rowIndex = position
            itemCLickLister.onItemClick(response)
            notifyDataSetChanged()
        }
//        if (rowIndex == position) {
//            holder.selectTime.visibility = View.GONE
//            holder.selectImage.visibility = View.VISIBLE
//        } else {
//            holder.selectTime.visibility = View.VISIBLE
//            holder.selectImage.visibility = View.GONE
//        }


    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val locationOverdue = itemView.locationOverdue
        val cardView = itemView.cardView


    }

    interface ItemCLickLister {
        fun onItemClick(item: Response)
    }


}