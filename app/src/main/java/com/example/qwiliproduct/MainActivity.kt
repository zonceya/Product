package com.example.qwiliproduct

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.qwiliproduct.databinding.ActivityMainBinding
import com.example.qwiliproduct.entities.ProductDetail
import com.example.qwiliproduct.ui.product.ProductAdapter
import com.example.qwiliproduct.ui.product.ProductViewModel
import com.example.qwiliproduct.ui.productdetails.ProductDetailsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProductViewModel by viewModels()
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()

        val productListObserver = Observer<List<ProductDetail>> { productList ->
            productAdapter.setProductList(productList)
        }
        viewModel.observeProductLiveData().observe(this, productListObserver)
        viewModel.getProducts()

        // Set item click listener
        productAdapter.setOnItemClickListener { product ->
            openProductDetails(product)
        }
    }

    private fun prepareRecyclerView() {
        productAdapter = ProductAdapter()
        val recyclerView: RecyclerView = binding.rvProducts
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter
    }

    private fun openProductDetails(product: ProductDetail) {
        val intent = Intent(this, ProductDetailsActivity::class.java)
        intent.putExtra("productId", product.id)
        startActivity(intent)
    }

}
