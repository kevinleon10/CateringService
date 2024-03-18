package com.hatchworks.cateringservice.model

import java.io.Serializable

data class FoodTime(
    val name: String?,
    val saucers: List<Saucer>?
) : Serializable