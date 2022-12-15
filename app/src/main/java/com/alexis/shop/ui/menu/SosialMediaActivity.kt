package com.alexis.shop.ui.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebViewClient
import com.alexis.shop.R
import com.alexis.shop.databinding.ActivitySosialMediaBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SosialMediaActivity : AppCompatActivity() {
    private lateinit var binding : ActivitySosialMediaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySosialMediaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getUrlLink = intent.getStringExtra(SOSIAL_URL)

        Log.d("LINKREDDE","$getUrlLink")

        getUrlLink.let {
            binding.webSosial.webViewClient = WebViewClient()
            binding.webSosial.settings.javaScriptEnabled = true
            binding.webSosial.loadUrl(it.orEmpty())
        }
    }

    companion object{
        const val SOSIAL_URL = "SOCIAL_URL"
    }
}