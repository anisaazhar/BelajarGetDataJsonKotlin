package com.tiara.restorancrudkotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tiara.restorancrudkotlin.R
import com.tiara.restorancrudkotlin.response.RincianMakanan
import kotlinx.android.synthetic.main.item_layout.view.*

class MakananAdapter (var data : List<RincianMakanan>) : RecyclerView.Adapter<MakananAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {

        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_layout, parent, false)

        return MyHolder(view)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder?.textview?.text = data.get(position).weight
        holder?.textview2?.text = data.get(position).createdDate
    }

    class MyHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

            var textview = itemView?.textName
            var textview2 = itemView?.textHarga

    }
}