package com.promobi.assignment.ui

import android.annotation.SuppressLint
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.promobi.assignment.R
import com.promobi.assignment.models.Book
import kotlinx.android.synthetic.main.item_book.view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by Vishwajit on 31/07/18.
 */
class BookAdapter(var itemList: ArrayList<Book>) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false))


    override fun getItemCount(): Int = itemList.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvTitle?.text = itemList[position].title
        holder.tvRank?.text = itemList[position].rank.toString()
        holder.tvContributor?.text = itemList[position].contributor
        holder.tvPublisher?.text = "Published by ${itemList[position].publisher}"
        holder.tvDescription?.text = itemList[position].description
        Glide.with(holder.ivImage!!.context).load(itemList[position].bookImage).into(holder.ivImage)
        holder.itemView.setOnClickListener { EventBus.getDefault().post(itemList[position]) }
    }

    class ViewHolder(view: View?) : RecyclerView.ViewHolder(view) {
        val ivImage = view?.iv_image
        val tvTitle = view?.tv_title
        val tvRank = view?.tv_rank
        val tvContributor = view?.tv_contributor
        val tvPublisher = view?.tv_publisher
        val tvDescription = view?.tv_description
    }
}