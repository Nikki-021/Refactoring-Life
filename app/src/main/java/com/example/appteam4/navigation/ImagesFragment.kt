package com.example.appteam4.navigation

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.appteam4.databinding.FragmentImagesBinding
import com.example.appteam4.ui.adapter.AdapterProducts
import com.example.appteam4.ui.viewmodel.ProductTypesViewModel
import com.example.appteam4.ui.viewmodel.ProductsViewModel

class ImagesFragment : Fragment() {

    private var idProduct: Int? = null
    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ImagesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {bundle ->
            idProduct = bundle.getInt(ARG_PRODUCT_ID)
            getProduct(idProduct!!)
        }

        observeState()
        actions()
    }

    private fun initRecyclerView(product: ProductByIdResponse){
        viewModel.viewModelScope.launch {
            binding.recyclerView.adapter = ProductImagesAdapter(product)
        }
    }

    private fun getProduct(id: Int){
        viewModel.getSimilarProducts(id)
    }

    private fun hideLoading(){
        binding.loadingScreenImages.rlLoading.visibility = View.GONE
    }

    private fun showLoading(){
        binding.loadingScreenImages.rlLoading.visibility = View.VISIBLE
    }

    private fun observeState(){
        viewModel.data.observe(viewLifecycleOwner){ data ->
            when(data){
                is StateProductById.Success -> {
                    initRecyclerView(data.info)
                    render(data.info)
                }

                is StateProductById.Error -> hideLoading()

                is StateProductById.Loading -> showLoading()
            }
        }
    }

    private fun render(value: ProductByIdResponse){
        binding.imagesTvTitle.text = value.name
        binding.ivProductPrice.text = value.price.toString().transformPrice(value.currency?: "")
    }

    private fun actions(){
        binding.arrowButton.setOnClickListener {
            activity?.finish()
        }

        binding.buyProductButton.setOnClickListener{
            val myIntent = Intent(activity, ConfirmationActivity::class.java)
            startActivity(myIntent)
        }

        binding.productButton.setOnClickListener {
            binding.productButton.setBackgroundResource(R.drawable.bg_btn_pressed)
            binding.colorsButton.setBackgroundResource(R.drawable.bg_btn_normal)
            binding.similarButton.setBackgroundResource(R.drawable.bg_btn_normal)
        }

        binding.colorsButton.setOnClickListener {
            binding.colorsButton.setBackgroundResource(R.drawable.bg_btn_pressed)
            binding.productButton.setBackgroundResource(R.drawable.bg_btn_normal)
            binding.similarButton.setBackgroundResource(R.drawable.bg_btn_normal)
        }

        binding.similarButton.setOnClickListener {
            binding.similarButton.setBackgroundResource(R.drawable.bg_btn_pressed)
            binding.productButton.setBackgroundResource(R.drawable.bg_btn_normal)
            binding.colorsButton.setBackgroundResource(R.drawable.bg_btn_normal)

            val myIntent = Intent(activity, SimilarActivity::class.java)
            val bundle = Bundle()
            bundle.putInt(ARG_PRODUCT_ID, idProduct?: -1)
            myIntent.putExtras(bundle)
            startActivity(myIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        fun newInstance(productId: Int): ImageSFragment {
            val fragment = ImageSFragment()
            val bundle = Bundle().apply {
                putInt(ARG_PRODUCT_ID, productId)
            }
            fragment.arguments = bundle
            return fragment
        }
    }
}