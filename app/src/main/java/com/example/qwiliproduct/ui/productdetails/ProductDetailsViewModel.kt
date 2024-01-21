package com.example.qwiliproduct.ui.productdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.qwiliproduct.entities.ProductDetail
import com.example.qwiliproduct.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailsViewModel : ViewModel() {

    private val productDetailsLiveData = MutableLiveData<ProductDetail>()
    val productDetail: LiveData<ProductDetail> get() = productDetailsLiveData
    private val loadingLiveData = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = loadingLiveData

    fun getProductDetails(productId: Int) {
        loadingLiveData.value = true // Set loading to true before making the API call
        ApiClient.create().getProductDetail(productId).enqueue(object : Callback<ProductDetail> {
            override fun onResponse(call: Call<ProductDetail>, response: Response<ProductDetail>) {
                loadingLiveData.value = false // Set loading to false after receiving the response
                if (response.isSuccessful) {
                    productDetailsLiveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ProductDetail>, t: Throwable) {
                loadingLiveData.value = false // Set loading to false on failure
                Log.d("TAG", t.message.toString())
            }
        })
    }

    fun observeProductDetails(): LiveData<ProductDetail> {
        return productDetailsLiveData
    }

}
