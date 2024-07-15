package com.example.appteam4

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
        println("token, $token")
        viewModelProductTypes = ProductTypesViewModel(token.toString())
        viewModelProducts = ProductsViewModel(token.toString())
        viewModelDailyOffer = DailyOfferViewModel(token.toString())

        showRecyclerViews()
        callProducts()
        observerProducts()
        actions()
    }

    private fun callProducts() {
        viewModelProductTypes.getProductTypes()
        viewModelProducts.getProducts()
        viewModelDailyOffer.getProductDailyOffer()
    }

    private fun observerProducts() {
        viewModelProductTypes.data.observe(this) {
            if (it == null) {
                Toast.makeText(this, "Respuesta del servidor: $it", Toast.LENGTH_SHORT).show()
            } else {
                adapterCategory.updateData(it.productTypes)
            }
        }
        viewModelProducts.data.observe(this) {
            if (it == null) {
                Toast.makeText(this, "Respuesta del servidor: $it", Toast.LENGTH_SHORT).show()
            } else {
                adapterProducts.updateData(it.products)
            }
        }
        viewModelDailyOffer.data.observe(this) {
            if (it == null) {
                Toast.makeText(this, "Respuesta del servidor: $it", Toast.LENGTH_SHORT).show()
            } else {
                binding.tvCategoyOffer.text = it.productType.descripcion
                binding.tvNameProductOffer.text = it.name
                binding.tvPriceProductOffer.text = it.currency + it.price
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
            Toast.makeText(this, "$category presionado", Toast.LENGTH_SHORT).show()
        }
        adapterProducts.onItemClick = { product ->
            Toast.makeText(this, "$product presionado", Toast.LENGTH_SHORT).show()
        }
    }
}
