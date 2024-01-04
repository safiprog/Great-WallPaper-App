package com.example.greatwallpaper.presentation.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.greatwallpaper.R
import com.example.greatwallpaper.domain.entity.WallpaperLink

class myAdaptor(private var dataset:List<WallpaperLink>,var clickLisner: ClickLisner):RecyclerView.Adapter<myAdaptor.myViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myAdaptor.myViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return myViewHolder(view)
    }

    override fun onBindViewHolder(holder: myAdaptor.myViewHolder, position: Int) {
        Glide.with(holder.img.context).load(dataset[position].wallpaperDLink).into(holder.img)
        holder.img.setOnClickListener {
            clickLisner.clickListnerAt(holder.img.drawable)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }
    inner class myViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val img:AppCompatImageView
        init {
            img=itemView.findViewById(R.id.imgView)
        }



    }

//    fun updateDataSet(dataset: List<WallpaperLink>){
//        dataset.
//    }
}