package com.example.appteam4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appteam4.databinding.ActivityHomeBinding
import com.example.appteam4.ui.adapter.AdapterCategory
import com.example.appteam4.ui.adapter.AdapterProducts
import com.example.appteam4.ui.viewmodel.ProductsViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<ProductsViewModel>()

    private val adapterCategoryAdapter by lazy {
        AdapterCategory()
    }

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
        showRecyclerViews()
        infoOffer()
        callProducts()
        observerProducts()
    }

    private fun callProducts(){
        viewModel.getProducts()
    }

    private fun observerProducts(){
        viewModel.data.observe(this){
            System.out.println("products: $it")
        }
    }

    private fun showRecyclerViews() {
        categoryRecyclerView()
        productsRecyclerView()
    }

    private fun categoryRecyclerView() {
        binding.recyclerViewCategory.apply {
            adapter = adapterCategoryAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }
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
}
