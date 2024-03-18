package com.hatchworks.cateringservice.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hatchworks.cateringservice.R
import com.hatchworks.cateringservice.databinding.ItemUpcomingOrderBinding
import com.hatchworks.cateringservice.model.UpcomingOrder
import com.hatchworks.cateringservice.util.getProgressDrawable
import com.hatchworks.cateringservice.util.loadImage

class UpcomingOrderListAdapter(private val upcomingOrderList: ArrayList<UpcomingOrder>) :
    RecyclerView.Adapter<UpcomingOrderListAdapter.UpcomingOrderViewHolder>() {

    fun updateUpcomingOrderList(newUpcomingOrderList: List<UpcomingOrder>) {
        upcomingOrderList.clear()
        upcomingOrderList.addAll(newUpcomingOrderList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingOrderViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemUpcomingOrderBinding>(
            inflater,
            R.layout.item_upcoming_order,
            parent,
            false
        )
        return UpcomingOrderViewHolder(view)
    }

    override fun getItemCount() = upcomingOrderList.size

    override fun onBindViewHolder(holder: UpcomingOrderViewHolder, position: Int) {
        holder.view.upcomingOrder = upcomingOrderList[position]
        holder.bind()
    }

    class UpcomingOrderViewHolder(var view: ItemUpcomingOrderBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind() {
            view.apply {
                cardLine.setBackgroundColor(
                    androidx.core.content.ContextCompat.getColor(
                        root.context,
                        R.color.colorAccent
                    )
                )
                cateringServiceImage.loadImage(
                    upcomingOrder?.imageUrl,
                    getProgressDrawable(root.context)
                )
            }
        }
    }
}