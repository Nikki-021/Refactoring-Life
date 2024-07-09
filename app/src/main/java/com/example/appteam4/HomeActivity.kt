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
import com.example.appteam4.ui.viewmodel.DailyOfferViewModel
import com.example.appteam4.ui.viewmodel.ProductTypesViewModel
import com.example.appteam4.ui.viewmodel.ProductsViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private val viewModelProducts by viewModels<ProductsViewModel>()
    private val viewModelProductTypes by viewModels<ProductTypesViewModel>()
    private val viewModelDailyOffer by viewModels<DailyOfferViewModel>()

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

    private fun callProducts() {
        viewModelProducts.getProducts()
        viewModelProductTypes.getProductTypes()
        viewModelDailyOffer.putProductDailyOffer(1)
    }

    private fun observerProducts() {
        viewModelProducts.data.observe(this) {
            if(it == null){
                System.out.println("products: $it")
            }else{
                adapterProductsAdapter.addItems(it.products.products)
            }

        }
        viewModelProductTypes.data.observe(this) {
            if(it == null){
                System.out.println("productTypes: $it")
            }else{
                adapterCategoryAdapter.addItems(it.productTypes)
            }
        }
        viewModelDailyOffer.data.observe(this){
            binding.tvCategoyOffer.text = it.productDailyOffer.productType.description
            binding.tvNameProductOffer.text = it.productDailyOffer.name
            binding.tvPriceProductOffer.text = it.productDailyOffer.price
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
