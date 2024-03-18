package com.hatchworks.cateringservice.model

import java.io.Serializable

data class Menu(
    val foodTimes: List<FoodTime>?
) : Serializable