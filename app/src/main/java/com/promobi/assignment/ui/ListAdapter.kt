package com.promobi.assignment.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.promobi.assignment.R
import com.promobi.assignment.models.Lists
import kotlinx.android.synthetic.main.item_category.view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by Vishwajit on 31/07/18.
 */
class ListAdapter(var itemList: ArrayList<Lists>) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false))

    override fun getItemCount(): Int = itemList.size

    fun updateItemList(itemList: ArrayList<Lists>) {
        this.itemList.clear()
        this.itemList.addAll(itemList)
        notifyDataSetChanged()
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCategory?.text = itemList[position].displayName
        holder.tvUpdated?.text = "Updated: ${itemList[position].updated}"
        Glide.with(holder.ivImage!!.context).load(itemList[position].listImage).into(holder.ivImage)
        holder.itemView.setOnClickListener { EventBus.getDefault().post(itemList[position]) }
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        val ivImage = view?.iv_image
        val tvCategory = view?.tv_category_name
        val tvUpdated = view?.tv_updated_at
    }
}