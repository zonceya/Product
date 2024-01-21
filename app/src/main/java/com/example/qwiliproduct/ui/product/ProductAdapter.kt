package com.example.qwiliproduct.ui.product

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.qwiliproduct.databinding.ProductLayoutBinding
import com.example.qwiliproduct.entities.ProductDetail

class ProductAdapter: RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var productList = ArrayList<ProductDetail>()
    private var onItemClick: ((ProductDetail) -> Unit)? = null
    fun setProductList(productList: List<ProductDetail>) {
        this.productList.clear()
        this.productList.addAll(productList)
        notifyDataSetChanged()
    }
    class ViewHolder(val binding : ProductLayoutBinding) : RecyclerView.ViewHolder(binding.root)  {}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ProductLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        Glide.with(holder.itemView)
            .load(productList[position].thumbnail)
            .into(holder.binding.productImage)
        holder.binding.productName.text = productList[position].title
        onItemClick?.let { listener ->
            holder.itemView.setOnClickListener {
                listener(product)
            }
        }
    }
    fun setOnItemClickListener(listener: (ProductDetail) -> Unit) {
        onItemClick = listener
    }
    override fun getItemCount(): Int {
        return productList.size
    }
}