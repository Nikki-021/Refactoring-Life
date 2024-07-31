package com.example.appteam4.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appteam4.R
import com.example.appteam4.data.Product
import com.example.appteam4.databinding.ItemRecyclerviewProductsSearchBinding
import com.example.appteam4.ui.viewmodel.ProductFavoriteViewModel
import com.squareup.picasso.Picasso

class AdapterProductsSearch(
    var products: List<Product>,
    private val viewModelProductFavorite: ProductFavoriteViewModel,
    private val removeFromFavorites: (Product) -> Unit
) :
    RecyclerView.Adapter<AdapterProductsSearch.ViewHolder>() {
    private var filterProductList: List<Product> = products

    inner class ViewHolder(private val binding: ItemRecyclerviewProductsSearchBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Product) {
            binding.tvName.text = item.name
            binding.tvDescription.text = item.description
            binding.tvPrice.text = item.currency + item.price.toString()
            Picasso.get().load(item.image).into(binding.img)

            val favoriteImageRes = if (item.isFavorite) {
                R.drawable.heart_blue_actived
            } else {
                R.drawable.heart_blue
            }
            binding.btnFavorites.setImageResource(favoriteImageRes)

            binding.btnFavorites.setOnClickListener {
                item.isFavorite = !item.isFavorite
                viewModelProductFavorite.updateProductFavorite(item.idProduct)
                if (!item.isFavorite) {
                    removeFromFavorites(item)
                }
                notifyItemChanged(adapterPosition)
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

    fun updateData(newProducts: List<Product>) {
        products = newProducts
        filterProductList = newProducts
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