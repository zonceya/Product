package com.example.qwiliproduct.entities

import com.example.qwiliproduct.entities.ProductDetail

data class Product(
    val limit: Int,
    val products: List<ProductDetail>,
    val skip: Int,
    val total: Int
)