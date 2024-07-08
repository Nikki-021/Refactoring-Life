package com.example.appteam4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appteam4.data.Product
import com.example.appteam4.data.Products
import com.example.appteam4.databinding.ItemRecyclerviewProductsBinding
import com.squareup.picasso.Picasso

class AdapterProducts() : RecyclerView.Adapter<AdapterProducts.ViewHolder>() {

    private val listProducts = mutableListOf<Product>()

    fun addItems(items: List<Product>) {
        listProducts.addAll(items)
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val binding: ItemRecyclerviewProductsBinding)
        :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.itemTitle.text = item.name
            binding.itemPrice.text = item.price
            Picasso.get().load(item.image).into(binding.itemImage)
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

    override fun getItemCount(): Int = listProducts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listProducts[position])
    }
}
