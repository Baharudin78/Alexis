package com.alexis.shop.ui.checkout.address

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
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
import com.alexis.shop.ui.menu.address.AddAddressFragment
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
    lateinit var inputText : String
    override fun getViewBinding(): ActivityCityBinding = ActivityCityBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = SheredPreference(this)
    }

    override fun main() {
        sharedPref = SheredPreference(this)
        inputText = binding.etKec.text.toString()
        Log.d("INPUTAN", inputText)
        cityAdapter = CityAdapter(binding.root.context, this)
        with(binding.rvSearcing) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = cityAdapter
        }
        binding.btCarikota.setOnClickListener {
            Log.d("INPUTAN", inputText)
            getDataCity()
        }
    }

    private fun getDataCity() {
        viewModel.getCitySearch("surabaya").observe(this) { response ->
            Log.d("INPUTAN", inputText)
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
        item as CityItemModel
        val intent = Intent(this, AddAddressFragment::class.java).apply {
            putExtra(AddAddressFragment.ADDRESS, item as CityItemModel)
        }
        setResult(100, intent)
        finish()
        Toast.makeText(binding.root.context, item.fullName, Toast.LENGTH_SHORT).show()
    }
}