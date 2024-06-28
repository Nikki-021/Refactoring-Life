package com.example.appteam4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appteam4.databinding.ActivityHomeBinding
import com.example.appteam4.databinding.ItemRecyclerviewCategoryBinding
import com.example.appteam4.ui.adapter.Products
import com.example.appteam4.ui.adapter.RecyclerViewCategory
import com.example.appteam4.ui.adapter.RecyclerViewProducts

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val recyclerViewCategoryAdapter by lazy {
        RecyclerViewCategory()
    }

    private val recyclerViewProductsAdapter by lazy {
        RecyclerViewProducts()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        showRecyclerViews()
        infoOffer()
    }

    private fun showRecyclerViews() {
        categoryRecyclerView()
        productsRecyclerView()
    }

    private fun categoryRecyclerView() {
        binding.recyclerViewCategory.apply {
            adapter = recyclerViewCategoryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        val itemCategorys = listOf("hola", "quetal", "adios", "category1", "category2", "category3")
        recyclerViewCategoryAdapter.addItems(itemCategorys)
    }

    private fun productsRecyclerView() {
        binding.recyclerViewProducts.apply {
            adapter = recyclerViewProductsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
        val productsList = listOf(
            Products("Product 1", "image1.jpg", "$" + "10.00"),
            Products("Product 2", "image2.jpg", "$" + "20.00"),
            Products("Product 3", "image3.jpg", "$" + "30.00"),
            Products("Product 1", "image1.jpg", "$" + "10.00"),
            Products("Product 2", "image2.jpg", "$" + "20.00"),
            Products("Product 3", "image3.jpg", "$" + "30.00")
        )
        recyclerViewProductsAdapter.addItems(productsList)
    }

    private fun infoOffer() {
        binding.tvCategoyOffer.text = "Rusty"
        binding.tvNameProductOffer.text = "Buzo canguro capucha con cordon"
        binding.tvPriceProductOffer.text = "$" + "52.00"
    }
}