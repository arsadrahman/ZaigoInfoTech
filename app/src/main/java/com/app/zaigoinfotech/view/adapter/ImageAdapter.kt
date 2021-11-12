package com.app.zaigoinfotech.view.adapter

import android.content.Context
import android.net.Uri
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.RecyclerView
import com.app.zaigoinfotech.BuildConfig
import com.app.zaigoinfotech.R
import com.app.zaigoinfotech.model.Data
import com.bumptech.glide.Glide
import java.io.File
import java.util.*

class ImageAdapter(val context: Context, val imagePathList: List<String>) :
    RecyclerView.Adapter<ImageAdapter.RecyclerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.listimageitem, parent, false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val imagePath: String = imagePathList.get(position)
        var uri: Uri = FileProvider.getUriForFile(
            Objects.requireNonNull(context),
            BuildConfig.APPLICATION_ID + ".provider", File(imagePath)
        );
        Glide
            .with(context)
            .load(uri)
            .centerCrop()
            .placeholder(R.drawable.ic_baseline_image_24)
            .into(holder.imageView);
    }

    override fun getItemCount(): Int = imagePathList.size

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageViewOne)


    }
}