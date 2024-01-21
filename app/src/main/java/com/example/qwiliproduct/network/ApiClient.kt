package com.example.qwiliproduct.network

import com.example.qwiliproduct.utilities.Constants
import retrofit2.Retrofit
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    fun create(): ProductService{
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL).build()
        return retrofit.create(ProductService::class.java)
}}