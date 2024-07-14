package com.example.appteam4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
    private val viewModelDailyOffer by viewModels<DailyOfferViewModel>()
    private lateinit var viewModelProductTypes: ProductTypesViewModel
    private lateinit var viewModelProducts: ProductsViewModel

    private lateinit var adapterCategory: AdapterCategory

    private val adapterProductsAdapter by lazy {
        AdapterProducts()
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
        val token = intent.getStringExtra("TOKEN")
        println("token, $token")
        viewModelProductTypes = ProductTypesViewModel(token.toString())
        viewModelProducts = ProductsViewModel(token.toString())

        showRecyclerViews()
        infoOffer()
        callProducts()
        observerProducts()
        actions()
    }

    private fun callProducts() {
        viewModelProductTypes.getProductTypes()
        viewModelProducts.getProducts()
        //viewModelDailyOffer.putProductDailyOffer()
    }

    private fun observerProducts() {
        viewModelProductTypes.data.observe(this) {
            if (it == null) {
                println("productTypes: $it")
            } else {
                println("productTypesOkOKOK: $it")
                adapterCategory.updateData(it.productTypes)
            }
        }
        viewModelProducts.data.observe(this) {
            if (it == null) {
                println("products: $it")
            } else {
                println("products OK: $it")
                //adapterCategoryAdapter.addItems(it.products.products)
            }
        }
    }

    private fun showRecyclerViews() {
        categoryRecyclerView()
        productsRecyclerView()
    }

    private fun categoryRecyclerView() {
        adapterCategory = AdapterCategory(emptyList())
        binding.recyclerViewCategory.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerViewCategory.adapter = adapterCategory
    }

    private fun productsRecyclerView() {
        binding.recyclerViewProducts.apply {
            adapter = adapterProductsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun infoOffer() {
        binding.tvCategoyOffer.text = "Rusty"
        binding.tvNameProductOffer.text = "Buzo canguro capucha con cordon"
        binding.tvPriceProductOffer.text = "$" + "52.00"
    }

    private fun actions(){
        adapterCategory.onItemClick = { category ->
            Toast.makeText(this, "$category presionado", Toast.LENGTH_SHORT).show()
        }
    }
}
