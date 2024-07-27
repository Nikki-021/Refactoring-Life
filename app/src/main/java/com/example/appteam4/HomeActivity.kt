package com.example.appteam4

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appteam4.databinding.ActivityHomeBinding
import com.example.appteam4.ui.adapter.AdapterCategory
import com.example.appteam4.ui.adapter.AdapterProducts
import com.example.appteam4.ui.viewmodel.DailyOfferViewModel
import com.example.appteam4.ui.viewmodel.ProductTypesViewModel
import com.example.appteam4.ui.viewmodel.ProductsViewModel
import com.squareup.picasso.Picasso

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private lateinit var viewModelProductTypes: ProductTypesViewModel
    private lateinit var viewModelProducts: ProductsViewModel
    private lateinit var viewModelDailyOffer: DailyOfferViewModel

    private lateinit var adapterCategory: AdapterCategory
    private lateinit var adapterProducts: AdapterProducts
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
        val token = intent.getStringExtra("TOKEN")
        println("token $token")
        viewModelProductTypes = ProductTypesViewModel(token.toString())
        viewModelProducts = ProductsViewModel(token.toString())
        viewModelDailyOffer = DailyOfferViewModel(token.toString())

        showRecyclerViews()
        callProducts()
        observerProducts()
        actions()
        navigation()
    }

    private fun callProducts() {
        viewModelProductTypes.getProductTypes()
        viewModelProducts.getProducts()
        viewModelDailyOffer.getProductDailyOffer()
    }

    @SuppressLint("SetTextI18n")
    private fun observerProducts() {
        viewModelProductTypes.data.observe(this) {
            if (it != null) {
                adapterCategory.updateData(it.productTypes)
            }
        }
        viewModelProducts.data.observe(this) {
            if (it != null) {
                adapterProducts.updateData(it.products)
            }
        }
        viewModelDailyOffer.data.observe(this) {
            if (it != null) {
                binding.tvCategoyOffer.text = it.productType.descripcion
                binding.tvNameProductOffer.text = it.name
                binding.tvPriceProductOffer.text = it.currency + it.price
                Picasso.get().load(it.images[0].link).into(binding.imgProductOffer)
            }
        }
    }

    private fun showRecyclerViews() {
        categoryRecyclerView()
        productsRecyclerView()
    }

    private fun categoryRecyclerView() {
        adapterCategory = AdapterCategory(emptyList())
        binding.recyclerViewCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCategory.adapter = adapterCategory
    }

    private fun productsRecyclerView() {
        adapterProducts = AdapterProducts(emptyList())
        binding.recyclerViewProducts.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewProducts.adapter = adapterProducts
    }

    private fun actions() {
        adapterCategory.onItemClick = { category ->
            adapterProducts.filterByCategory(category)
        }
        adapterProducts.onItemClick = { product ->
            Toast.makeText(this, "$product presionado", Toast.LENGTH_SHORT).show()
        }
        binding.searchViewProducts.setOnClickListener {
            Toast.makeText(this, "search ....", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigation() {
        binding.imgProductOffer.setOnClickListener {
            val intent = Intent(this, ActivityDescriptionHome::class.java)
            startActivity(intent)
        }
    }
}
