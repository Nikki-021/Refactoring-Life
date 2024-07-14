package com.example.appteam4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appteam4.data.ProductTypes
import com.example.appteam4.databinding.ItemRecyclerviewCategoryBinding

class AdapterCategory(private var productTypes: List<ProductTypes>) :
    RecyclerView.Adapter<AdapterCategory.ViewHolder>() {

    var onItemClick: ((String) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemRecyclerviewCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductTypes) {
            binding.titleCategory.text = item.description
            binding.cardViewCategory.setOnClickListener {
                onItemClick?.invoke(item.description)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecyclerviewCategoryBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = productTypes.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productTypes[position])
    }

    fun updateData(newProductTypes: List<ProductTypes>) {
        productTypes = newProductTypes
        notifyDataSetChanged()
    }
}
