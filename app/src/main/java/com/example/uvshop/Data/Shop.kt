package com.example.uvshop.Data


data class Shop(
    val name: String = "",
    val category: String = "",
    val description: String = "",
    val products: List<Product> = emptyList(),
    val reference: String = ""
)


