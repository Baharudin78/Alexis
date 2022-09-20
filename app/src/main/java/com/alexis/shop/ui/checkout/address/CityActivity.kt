package com.alexis.shop.ui.checkout.address

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.BaseActivity
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.data.remote.network.ApiResponse
import com.alexis.shop.databinding.ActivityCityBinding
import com.alexis.shop.domain.model.city.CityItemModel
import com.alexis.shop.ui.account.adapter.CityAdapter
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.prefs.SheredPreference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CityActivity : BaseActivity<ActivityCityBinding>(), OnClickItem {

    private val viewModel : CityViewModel by viewModels()
    private var allCity = ArrayList<CityItemModel>()
    private lateinit var cityAdapter : CityAdapter
    @Inject
    lateinit var sharedPref : SheredPreference

    override fun getViewBinding(): ActivityCityBinding = ActivityCityBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = SheredPreference(this)
    }

    override fun main() {
        sharedPref = SheredPreference(this)
        var nama = "Semarang"

        var job : Job? = null

        cityAdapter = CityAdapter(binding.root.context, this)
        with(binding.rvSearching) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = cityAdapter
        }
        binding.searchBar.addTextChangedListener { editable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500)
                editable?.let {
                    if (editable.toString().isNotEmpty()) {
                        viewModel.getCitySearch(token = "Bearer ${sharedPref.getToken()}", editable.toString())
                    }
                }
            }
        }
        getDataCity(nama)
    }

    private fun getDataCity(nama : String) {
        viewModel.getCitySearch(token = "Bearer ${sharedPref.getToken()}", nama).observe(this) { response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val getCityValue = response.data?.data as ArrayList<CityItemModel>
                        allCity = getCityValue
                        Log.d("CITYRESPI", "$getCityValue")
                        cityAdapter.setData(getCityValue)
                    }
                    is Resource.Error -> {
                        Log.d("ERRORRS", sharedPref.getToken())
                        Toast.makeText(
                            binding.root.context,
                            "Get City Failed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }

    override fun onClick(item: Any) {
        Toast.makeText(binding.root.context, "clicked", Toast.LENGTH_SHORT).show()
    }
}