package com.example.appteam4.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appteam4.R
import com.example.appteam4.databinding.FragmentImagesBinding

class ImagesFragment : Fragment() {

    private var idProduct: Int? = null
    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun actions(){
        binding.arrowButton.setOnClickListener {
            activity?.finish()
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

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}