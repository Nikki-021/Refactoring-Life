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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvImafesFragment.setOnClickListener {
          loadFragment(ImagesFragment())
        }

        binding.tvDescriptionFragment.setOnClickListener {
            loadFragment(DescriptionFragment())
        }

        binding.tvFinancingFragment.setOnClickListener {
            loadFragment(FinancingFragment())
        }

        binding.tvCommentsFragment.setOnClickListener {
            loadFragment(CommentsFragment())
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment).commit()
    }
}
