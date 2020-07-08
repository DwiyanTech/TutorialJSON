package com.dwiyanstudio.tutorialjson

import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.card_view.view.*

class AdapterData(private val dataList : ArrayList<JsonData>) : RecyclerView.Adapter<AdapterData.AdapterDataViewHolder>() {

    class AdapterDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(jsonData: JsonData){
            with(itemView){
                Glide.with(itemView.context)
                    .load(jsonData.urlThumbnail)
                    .apply { RequestOptions().override(128,120) }.into(img_photo)
                text_title.text = jsonData.title
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return  AdapterDataViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: AdapterDataViewHolder, position: Int) {
       holder.bind(dataList[position])
    }

}