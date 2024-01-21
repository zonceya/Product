package com.example.qwiliproduct.network

import com.example.qwiliproduct.entities.Product
import com.example.qwiliproduct.entities.ProductDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductService {
    @GET("products")
    fun getProducts(): Call<Product>

    @GET("products/{productId}")
    fun getProductDetail(@Path("productId") productId: Int): Call<ProductDetail>

}
