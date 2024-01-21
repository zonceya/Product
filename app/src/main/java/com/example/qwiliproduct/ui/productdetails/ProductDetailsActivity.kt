package com.example.qwiliproduct.ui.productdetails

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qwiliproduct.R
import com.example.qwiliproduct.databinding.ProductDetailsLayoutBinding

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ProductDetailsLayoutBinding
    private lateinit var viewModel: ProductDetailsViewModel
    private lateinit var progressBar: ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProductDetailsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        progressBar = findViewById(R.id.productDetailsProgressBar)
        // Initialize ViewModel
        viewModel = ViewModelProvider(this).get(ProductDetailsViewModel::class.java)
        progressBar.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        // Extract productId from intent
        val productId = intent.getIntExtra("productId", -1)

        // Observe LiveData from ViewModel
        viewModel.observeProductDetails().observe(this, Observer { productDetails ->
            progressBar.visibility = View.GONE
            binding.productTitle.text = productDetails.title
            binding.productDescription.text = productDetails.description
            // Update UI with product details
            if (productDetails != null) {
                // Load image using Glide
                Glide.with(this)
                    .load(productDetails.thumbnail)
                    .into(binding.productThumbnail)

                binding.productTitle.text = "Product: ${productDetails.title}"
                binding.productDescription.text =  "Description: ${productDetails.description}"
                binding.productBrand.text ="Brand: ${productDetails.brand}"
                binding.productCategory.text ="Category: ${productDetails.category}"
                binding.productPrice.text = "Price: ${productDetails.price.toString()}"
                binding.productStock.text = "Stock: ${productDetails.stock.toString()}"
                binding.productRating.text = "Rating: ${productDetails.rating.toString()}"
            }
        })
        progressBar.visibility = View.VISIBLE
        // Fetch product details
        if (productId != -1) {
            viewModel.getProductDetails(productId)
        } else {
          return
        }
    }
}