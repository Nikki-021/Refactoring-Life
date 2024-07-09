package com.example.appteam4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appteam4.data.Product
import com.example.appteam4.data.ProductType
import com.example.appteam4.databinding.ItemRecyclerviewCategoryBinding

class AdapterCategory : RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    private val listProductTypes = mutableListOf<ProductType>()

    fun addItems(items: List<ProductType>) {
        listProductTypes.addAll(items)
    }
    inner class ViewHolder(private val binding: ItemRecyclerviewCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductType) {
            binding.titleCategory.text = item.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerviewCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = listProductTypes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listProductTypes[position])
    }
}
