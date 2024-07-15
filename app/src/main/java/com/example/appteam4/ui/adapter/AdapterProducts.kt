package com.example.appteam4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appteam4.data.Product
import com.example.appteam4.data.Products
import com.example.appteam4.databinding.ItemRecyclerviewProductsBinding
import com.squareup.picasso.Picasso

class AdapterProducts(private var products: List<Product>) :
    RecyclerView.Adapter<AdapterProducts.ViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemRecyclerviewProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.itemTitle.text = item.name
            binding.itemPrice.text = item.currency + item.price.toString()
            Picasso.get().load(item.image).into(binding.itemImage)
            binding.cardViewProducts.setOnClickListener {
                onItemClick?.invoke(item.name)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerviewProductsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun updateData(newProductTypes: List<Product>) {
        products = newProductTypes
        notifyDataSetChanged()
    }
}
