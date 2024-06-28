package com.example.appteam4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appteam4.databinding.ItemRecyclerviewCategoryBinding

class RecyclerViewCategory : RecyclerView.Adapter<RecyclerViewCategory.ViewHolder>() {

    private val listCategory = mutableListOf<String>()

    fun addItems(items: List<String>) {
        listCategory.addAll(items)
    }

    inner class ViewHolder(private val binding: ItemRecyclerviewCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: String) {
            binding.titleCategory.text = item
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

    override fun getItemCount(): Int = listCategory.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listCategory[position])
    }
}
