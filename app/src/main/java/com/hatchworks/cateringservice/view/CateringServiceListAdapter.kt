package com.hatchworks.cateringservice.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hatchworks.cateringservice.R
import com.hatchworks.cateringservice.databinding.ItemCateringServiceBinding
import com.hatchworks.cateringservice.model.CateringService
import com.hatchworks.cateringservice.util.MenuHolder
import com.hatchworks.cateringservice.util.getProgressDrawable
import com.hatchworks.cateringservice.util.loadImage

class CateringServiceListAdapter(private val cateringServiceList: ArrayList<CateringService>) :
    RecyclerView.Adapter<CateringServiceListAdapter.CateringServiceViewHolder>() {

    fun updateCateringServiceList(newCateringServiceList: List<CateringService>) {
        cateringServiceList.clear()
        cateringServiceList.addAll(newCateringServiceList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CateringServiceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCateringServiceBinding>(
            inflater,
            R.layout.item_catering_service,
            parent,
            false
        )
        return CateringServiceViewHolder(view)
    }

    override fun getItemCount() = cateringServiceList.size

    override fun onBindViewHolder(holder: CateringServiceViewHolder, position: Int) {
        holder.view.cateringService = cateringServiceList[position]
        holder.bind()
    }

    class CateringServiceViewHolder(var view: ItemCateringServiceBinding) :
        RecyclerView.ViewHolder(view.root) {
        fun bind() {
            view.apply {
                cardLine.setBackgroundColor(
                    androidx.core.content.ContextCompat.getColor(
                        root.context,
                        R.color.colorAccent
                    )
                )
                cateringService?.let {
                    val imageUrl = cateringService?.imageUrls?.get(0)
                    cateringServiceImage.loadImage(
                        imageUrl,
                        getProgressDrawable(root.context)
                    )
                    cateringServiceLayout.setOnClickListener {
                        val action = HomeFragmentDirections.actionDetail(cateringService as CateringService)
                        MenuHolder.instance.setMenu((cateringService as CateringService).menu)
                        Navigation.findNavController(view.root).navigate(action)
                    }
                }
            }
        }
    }
}