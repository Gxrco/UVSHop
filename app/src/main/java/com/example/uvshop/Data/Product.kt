package com.example.uvshop.Data

import java.io.Serializable

data class Product(
    val name: String = "",
    val price: String = "",
    val description: String = "",
    val image: String? = null
)
