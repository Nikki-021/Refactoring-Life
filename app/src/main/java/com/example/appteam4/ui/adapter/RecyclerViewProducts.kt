package com.example.appteam4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appteam4.databinding.ItemRecyclerviewCategoryBinding
import com.example.appteam4.databinding.ItemRecyclerviewProductsBinding

class RecyclerViewProducts : RecyclerView.Adapter<RecyclerViewProducts.ViewHolder>() {

    private val listProducts = mutableListOf<Products>()

    fun addItems(items: List<Products>) {
        listProducts.addAll(items)
    }

    inner class ViewHolder(private val binding: ItemRecyclerviewProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Products) {
            binding.itemTitle.text = item.name
            binding.itemPrice.text = item.price
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
