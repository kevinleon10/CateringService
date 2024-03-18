package com.hatchworks.cateringservice.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hatchworks.cateringservice.R
import com.hatchworks.cateringservice.databinding.ItemFeaturedCateringServiceBinding
import com.hatchworks.cateringservice.model.FeaturedCateringService

class FeaturedCateringServiceListAdapter(private val featuredCateringServiceList: ArrayList<FeaturedCateringService>) :
    RecyclerView.Adapter<FeaturedCateringServiceListAdapter.FeaturedCateringServiceViewHolder>() {

    fun updateFeaturedCateringServiceList(newFeaturedCateringServiceList: List<FeaturedCateringService>) {
        featuredCateringServiceList.clear()
        featuredCateringServiceList.addAll(newFeaturedCateringServiceList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeaturedCateringServiceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemFeaturedCateringServiceBinding>(
            inflater,
            R.layout.item_featured_catering_service,
            parent,
            false
        )
        return FeaturedCateringServiceViewHolder(view)
    }

    override fun getItemCount() = featuredCateringServiceList.size

    override fun onBindViewHolder(holder: FeaturedCateringServiceViewHolder, position: Int) {
        holder.view.featureCateringService = featuredCateringServiceList[position]
        holder.bind()
    }

    class FeaturedCateringServiceViewHolder(var view: ItemFeaturedCateringServiceBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind() {
            val cateringServiceListAdapter = CateringServiceListAdapter(arrayListOf())
            view.cateringServiceList.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = cateringServiceListAdapter
            }
             view.featureCateringService?.cateringServices?.let {
                cateringServiceListAdapter.updateCateringServiceList(view.featureCateringService?.cateringServices!!)
             }
        }
    }
}