package com.example.appteam4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appteam4.data.Product
import com.example.appteam4.databinding.ItemRecyclerviewProductsSearchBinding
import com.squareup.picasso.Picasso

class AdapterProductsSearch(private var products: List<Product>) :
    RecyclerView.Adapter<AdapterProductsSearch.ViewHolder>() {
        private var filterProductList : List<Product> = products
    inner class ViewHolder(private val binding: ItemRecyclerviewProductsSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.tvName.text = item.name
            binding.tvDescription.text = item.description
            binding.tvPrice.text = item.currency + item.price.toString()
            Picasso.get().load(item.image).into(binding.img)
            binding.btnFavorites.setOnClickListener {
                if (item.isFavorite) {
                    !item.isFavorite
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemRecyclerviewProductsSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filterProductList[position])
    }

    override fun getItemCount(): Int = filterProductList.size

    fun updateData(newProductTypes: List<Product>) {
        products = newProductTypes
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filterProductList = if (query.isEmpty()) {
            products
        } else {
            products.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}