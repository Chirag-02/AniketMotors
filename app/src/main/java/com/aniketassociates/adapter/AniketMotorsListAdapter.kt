package com.aniketassociates.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aniketassociates.R
import com.aniketassociates.model.aniketMotorsModel.AniketMotorsListResponse
import kotlinx.android.synthetic.main.aniket_motors_adapter_item.view.*


class AniketMotorsListAdapter(
    var response: List<AniketMotorsListResponse.Data>,
    val context: Context,
    val itemCLickLister: ItemCLickLister
) :
    RecyclerView.Adapter<AniketMotorsListAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.aniket_motors_adapter_item, parent, false)
        return ViewHolder(v)
    }


    override fun getItemCount(): Int {
        return response.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val response: AniketMotorsListResponse.Data = response[position]
        holder.locationOverdue.text = response.registration_no

        holder.parentItem.setOnClickListener {
            itemCLickLister.onItemClick(response)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        val locationOverdue = itemView.textView46
        val parentItem = itemView.parentItem

    }

    interface ItemCLickLister {
        fun onItemClick(item: AniketMotorsListResponse.Data)
    }

    fun updateList(list: MutableList<AniketMotorsListResponse.Data>) {
        response = list
        notifyDataSetChanged()
    }


}