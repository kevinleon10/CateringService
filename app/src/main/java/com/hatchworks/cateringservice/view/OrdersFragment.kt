package com.hatchworks.cateringservice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.hatchworks.cateringservice.R
import com.hatchworks.cateringservice.databinding.FragmentOrdersBinding


class OrdersFragment : Fragment() {

    private lateinit var dataBinding: FragmentOrdersBinding
    private val tabCount = 2
    private val pageTitles = arrayListOf("Upcoming", "Past")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_orders, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataBinding.viewPager.adapter = PagerAdapter(this.requireActivity().supportFragmentManager)
    }

    inner class PagerAdapter(manager: FragmentManager) :
        FragmentStatePagerAdapter(manager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

        override fun getCount(): Int = tabCount

        override fun getItem(pos: Int): Fragment {
            return when (pos) {
                0 -> UpcomingOrdersFragment()
                else -> PastOrdersFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return pageTitles[position]
        }
    }
}


