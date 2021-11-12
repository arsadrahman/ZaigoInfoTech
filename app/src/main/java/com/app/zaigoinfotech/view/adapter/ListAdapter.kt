package com.app.zaigoinfotech.view.adapter

import android.content.Context
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.app.zaigoinfotech.R
import com.app.zaigoinfotech.model.Data
import com.bumptech.glide.Glide

class ListAdapter(val context: Context, val list: List<Data>) :
    RecyclerView.Adapter<ListAdapter.RecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.listitem, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val data: Data = list.get(position)
        holder.titleTv.text = data.title
        holder.startDate.text = "Start Date : ${data.startDate}"
        holder.endDate.text = "End Date : ${data.endDate}"
        holder.status.text = "Status : ${data.status}"
        holder.subscribe.text = "Subscribe : ${data.subscribe}"
        Glide
            .with(context)
            .load(data.image)
            .centerCrop()
            .into(holder.imageView);
    }

    override fun getItemCount(): Int = list.size

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_iv)
        val titleTv: TextView = itemView.findViewById(R.id.title_tv)
        val startDate: TextView = itemView.findViewById(R.id.startdate_tv)
        val endDate: TextView = itemView.findViewById(R.id.enddate_tv)
        val status: TextView = itemView.findViewById(R.id.status_tv)
        val subscribe: TextView = itemView.findViewById(R.id.subscribe_tv)

    }
}