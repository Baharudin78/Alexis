package com.alexis.shop.ui.shopping_bag

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexis.shop.R
import com.alexis.shop.databinding.ActivityReviewOrderBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ReviewOrderActivity : AppCompatActivity() {
    lateinit var binding : ActivityReviewOrderBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}