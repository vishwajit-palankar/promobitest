package com.promobi.assignment.ui

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.promobi.assignment.BR
import com.promobi.assignment.R
import com.promobi.assignment.models.BuyLink
import org.greenrobot.eventbus.EventBus

/**
 * Created by Vishwajit on 31/07/18.
 */
class BuyLinkAdapter(val data: List<BuyLink>) : RecyclerView.Adapter<BuyLinkAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.item_buy_link, parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position])

    class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BuyLink) {
            binding.setVariable(BR.link, data)
            binding.executePendingBindings()
            binding.root.setOnClickListener { EventBus.getDefault().post(data.url) }
        }
    }
}