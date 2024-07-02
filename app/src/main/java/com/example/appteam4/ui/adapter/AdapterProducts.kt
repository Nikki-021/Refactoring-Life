package com.example.appteam4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appteam4.databinding.ItemRecyclerviewProductsBinding

class AdapterProducts : RecyclerView.Adapter<AdapterProducts.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemRecyclerviewProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.itemTitle.text
            binding.itemPrice.text
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

    override fun getItemCount(): Int = 6

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}
