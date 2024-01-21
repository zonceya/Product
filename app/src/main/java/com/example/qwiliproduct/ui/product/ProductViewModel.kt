package com.example.qwiliproduct.ui.product

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qwiliproduct.entities.Product
import com.example.qwiliproduct.entities.ProductDetail
import com.example.qwiliproduct.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductViewModel: ViewModel() {
    private var productLiveData = MutableLiveData<List<ProductDetail>>()
    private var loadingLiveData = MutableLiveData<Boolean>()

    fun getProducts() {
        loadingLiveData.value = true // Set loading to true before making the API call

        ApiClient.create().getProducts().enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                loadingLiveData.value = false // Set loading to false after receiving the response

                if (response.body() != null) {
                    productLiveData.value = response.body()!!.products
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {
                loadingLiveData.value = false // Set loading to false in case of failure
                Log.d("TAG", t.message.toString())
            }
        })
    }

    fun observeProductLiveData(): LiveData<List<ProductDetail>> {
        return productLiveData
    }

}
