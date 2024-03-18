package com.hatchworks.cateringservice.model

import java.io.Serializable

data class Saucer(
    val name: String?,
    val type: String?,
    val imageUrl: String?,
    val description: String?
) : Serializable