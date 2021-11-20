package com.emploc.screens.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.emploc.R


class ViewPagerAdapter internal constructor(context: Context?, data: List<String>) : RecyclerView.Adapter<ViewPagerAdapter.ViewHolder>() {
    private val mData: List<String> = data
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_viewpager, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        when (position) {
            0 -> {
                holder.banner.setImageResource(R.drawable.ic_slide4)
                holder.title_txt.text = "Location Based Tasks"
                holder.sub_title.text = "Increase prouctivity and save time on the tasks assigned with real-time customer's location"
            }
            1 -> {
                holder.banner.setImageResource(R.drawable.ic_slide2)
                holder.title_txt.text = "Location Based Tasks"
                holder.sub_title.text = "This user friendly app enabled you to mark your attendance based on location with a single tap"

            }
            2 -> {
                holder.banner.setImageResource(R.drawable.ic_slide3)
                holder.title_txt.text = "Live Delivery Location"
                holder.sub_title.text = "Send your real-time delivery location to the customers"

            }
        }
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var banner: ImageView = itemView.findViewById(R.id.tvImage)
        var title_txt: TextView = itemView.findViewById(R.id.title_txt)
        var sub_title: TextView = itemView.findViewById(R.id.sub_title)

    }

}