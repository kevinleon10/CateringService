package com.hatchworks.cateringservice.model

import java.io.Serializable

data class CateringService(
    val name: String?,
    val type: String?,
    val imageUrls: List<String>?,
    val pricePerGuest: Int?,
    val description: String?,
    val preparationTime: String?,
    val minimumGuests: Int?,
    val maximumGuests: Int?,
    val priceRange: String?,
    val menu: Menu?,
    val timeAvailability: List<String>?
) : Serializable