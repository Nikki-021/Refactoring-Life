package com.example.appteam4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.appteam4.databinding.ActivityDescriptionHomeBinding
import com.example.appteam4.navigation.CommentsFragment
import com.example.appteam4.navigation.DescriptionFragment
import com.example.appteam4.navigation.FinancingFragment
import com.example.appteam4.navigation.ImagesFragment

class ActivityDescriptionHome : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionHomeBinding

    private val fragment1: ImagesFragment by lazy {
        ImagesFragment.newInstance()
    }
    private val fragment2: DescriptionFragment by lazy {
        DescriptionFragment.newInstance()
    }
    private val fragment3: FinancingFragment by lazy {
        FinancingFragment.newInstance()
    }
    private val fragment4: CommentsFragment by lazy {
        CommentsFragment.newInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvImafesFragment.setOnClickListener {
          showFragment(fragment1, ImagesFragment::class.java.toString())
        }

        binding.tvDescriptionFragment.setOnClickListener {
            showFragment(fragment2, DescriptionFragment::class.java.toString())
        }

        binding.tvFinancingFragment.setOnClickListener {
            showFragment(fragment3, FinancingFragment::class.java.toString())
        }

        binding.tvCommentsFragment.setOnClickListener {
            showFragment(fragment4, CommentsFragment::class.java.toString())
        }
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .add(binding.fragmentContainer.id, fragment, tag).commit()
    }
}
