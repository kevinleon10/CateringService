package com.hatchworks.cateringservice.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.hatchworks.cateringservice.R
import com.hatchworks.cateringservice.databinding.FragmentDetailBinding
import com.hatchworks.cateringservice.util.getProgressDrawable
import com.hatchworks.cateringservice.util.loadImage


class DetailFragment : Fragment() {

    private lateinit var dataBinding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding.apply {
            arguments?.let {
                cateringService = DetailFragmentArgs.fromBundle(it).cateringService
            }

            closeImage.setOnClickListener {
                this@DetailFragment.requireActivity().onBackPressed()
            }

            carouselView.setImageListener { position, imageView ->
                imageView.loadImage(
                    cateringService?.imageUrls?.get(position),
                    getProgressDrawable(root.context)
                )
            }
            cateringService?.imageUrls?.let {
                carouselView.pageCount = cateringService?.imageUrls!!.size
            }

            circleLayout.bringToFront()

            fillDropdown()

            dropdownLayout.setBackgroundColor(
                ContextCompat.getColor(
                    root.context,
                    R.color.colorAccent
                )
            )
        }
    }

    private fun fillDropdown() {

        var guestQuantity =
            arrayOf<String?>()

        dataBinding.apply {
            cateringService?.let {

                for (i in cateringService?.minimumGuests!!..cateringService?.maximumGuests!!) {
                    guestQuantity += "$i Guests"
                }

                val guestsAdapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
                    root.context,
                    R.layout.dropdown_menu_popup_item,
                    guestQuantity
                )

                guestsDropdown.setAdapter(guestsAdapter)
                guestsDropdown.setText(guestQuantity[0], false)

                cateringService?.timeAvailability?.let {
                    val datesAvailable = cateringService?.timeAvailability!!.toTypedArray()
                    val datesAdapter: ArrayAdapter<String?> = ArrayAdapter<String?>(
                        root.context,
                        R.layout.dropdown_menu_popup_item,
                        datesAvailable
                    )
                    datesDropdown.setAdapter(datesAdapter)
                    datesDropdown.setText(datesAvailable[0], false)
                }

                var price = cateringService?.minimumGuests!! * cateringService?.pricePerGuest!!
                hireButton.text = getString(R.string.hire_for, price)
                guestsDropdown.onItemClickListener = OnItemClickListener { _, _, _, _ ->
                    price = guestsDropdown.text.toString()
                        .split(" ")[0].toInt() * cateringService?.pricePerGuest!!
                    hireButton.text = getString(R.string.hire_for, price)
                }

            }
        }

    }

}
