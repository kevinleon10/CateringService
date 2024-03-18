package com.hatchworks.cateringservice.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hatchworks.cateringservice.R
import com.hatchworks.cateringservice.databinding.FragmentFoodTimeBinding
import com.hatchworks.cateringservice.model.FoodTime
import com.hatchworks.cateringservice.util.getProgressDrawable
import com.hatchworks.cateringservice.util.loadImage

class FoodTimeFragment(private var foodTime: FoodTime?) : Fragment() {

    private lateinit var dataBinding: FragmentFoodTimeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_food_time, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.apply {
            foodTime?.let {
                saucer = foodTime?.saucers?.get(0)
                firstItem.loadImage(
                    foodTime?.saucers?.get(0)?.imageUrl,
                    getProgressDrawable(root.context)
                )

                secondItem.loadImage(
                    foodTime?.saucers?.get(1)?.imageUrl,
                    getProgressDrawable(root.context)
                )

                thirdItem.loadImage(
                    foodTime?.saucers?.get(2)?.imageUrl,
                    getProgressDrawable(root.context)
                )
            }
        }
    }
}
