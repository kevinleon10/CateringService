package com.hatchworks.cateringservice.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.hatchworks.cateringservice.R
import com.hatchworks.cateringservice.databinding.ActivityMainBinding
import com.hatchworks.cateringservice.util.getVisibility

class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupNavigation()
        dataBinding.mainLayout.setBackgroundColor(Color.WHITE)
        dataBinding.viewLineSeparator.setBackgroundColor(
            ContextCompat.getColor(
                dataBinding.root.context,
                R.color.colorAccent
            )
        )
    }

    private fun setupNavigation() {
        val navController = Navigation.findNavController(this, R.id.navHostFragment)
        NavigationUI.setupWithNavController(dataBinding.bottomNavigationView, navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> setNavigationVisibility(
                    appBarVisible = true,
                    bottomNavigationViewVisible = true
                )
                R.id.ordersFragment -> setNavigationVisibility(
                    appBarVisible = true,
                    bottomNavigationViewVisible = true
                )
                R.id.profileFragment -> setNavigationVisibility(
                    appBarVisible = true,
                    bottomNavigationViewVisible = true
                )
                else -> setNavigationVisibility(
                    appBarVisible = false,
                    bottomNavigationViewVisible = false
                )
            }
        }
    }

    private fun setNavigationVisibility(
        appBarVisible: Boolean,
        bottomNavigationViewVisible: Boolean
    ) {
        dataBinding.appBar.visibility = getVisibility(appBarVisible)
        dataBinding.bottomNavigationView.visibility = getVisibility(bottomNavigationViewVisible)
    }

}
