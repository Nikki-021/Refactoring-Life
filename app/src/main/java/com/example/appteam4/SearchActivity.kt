package com.example.appteam4

import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appteam4.databinding.ActivitySearchBinding
import com.example.appteam4.ui.adapter.AdapterProductsSearch
import com.example.appteam4.ui.viewmodel.LoginViewModel
import com.example.appteam4.ui.viewmodel.ProductTypesViewModel
import com.example.appteam4.ui.viewmodel.ProductsOnlyFavoriteViewModel
import com.example.appteam4.ui.viewmodel.ProductsViewModel

class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding

    private lateinit var viewModelProducts: ProductsViewModel
    private lateinit var viewModelProductsOnlyFavorite: ProductsOnlyFavoriteViewModel
    private lateinit var adapterProductsSearch: AdapterProductsSearch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val token = intent.getStringExtra("TOKEN")
        println("token search $token")
        viewModelProducts = ProductsViewModel(token.toString())
        viewModelProductsOnlyFavorite = ProductsOnlyFavoriteViewModel(token.toString())

        callProducts()
        observerProducts()
        productsRecyclerView()
        actions()
    }
    private fun callProducts() {
        viewModelProducts.getProducts()
        viewModelProductsOnlyFavorite.getOnlyFavorite()
    }
    private fun observerProducts() {
        viewModelProducts.data.observe(this) {
            if (it != null) {
                adapterProductsSearch.updateData(it.products)
            }
        }
        viewModelProductsOnlyFavorite.data.observe(this) {
            if (it != null) {
                println("favorite $it")
            }else{
                println("sin favoritos")
            }
        }
    }
    private fun productsRecyclerView() {
        adapterProductsSearch = AdapterProductsSearch(emptyList())
        binding.recyclerViewProductsSearch.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerViewProductsSearch.adapter = adapterProductsSearch
    }
    private fun actions() {
        binding.iconFavorites.setOnClickListener {
            Toast.makeText(this, "is Favorite", Toast.LENGTH_SHORT).show()
        }
        binding.searchViewProducts.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterProductsSearch.filter(newText ?: "")
                println(newText)
                return true
            }
        })
    }
}