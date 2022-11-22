package com.alexis.shop.ui.menu.aboutus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.alexis.shop.BaseFragment
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentAboutUsBinding
import com.alexis.shop.domain.model.contact.ContactDataModel
import com.alexis.shop.ui.menu.contactus.ContactViewModel
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.justOut
import dagger.hilt.android.AndroidEntryPoint
import eightbitlab.com.blurview.RenderScriptBlur

@AndroidEntryPoint
class AboutUsFragment : BaseFragment<FragmentAboutUsBinding>() {
    private var contactModel : ContactDataModel? = null
    private val viewModel : ContactViewModel by viewModels()

    override fun getViewBinding() = FragmentAboutUsBinding.inflate(layoutInflater)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
    }

    override fun main() {
        super.main()
        blurView()
        getContact()
        setListener()
    }

    private fun getContact() {
        viewModel.getContact().observe(viewLifecycleOwner){response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        response.data?.let {
                            contactModel = it
                            setupView(it)
                        }
                    }
                    is Resource.Error -> {
                        Toast.makeText(binding.root.context, "Failed Get Helpcenter", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupView(data : ContactDataModel) {
        binding.txtWa.text = data.whatsapp.orEmpty()
        binding.txtMail.text = data.email.orEmpty()
    }

    private fun setListener() {
        binding.btnCancel.setOnClickListener {

            Looper.myLooper()?.let { it1 ->
                Handler(it1).postDelayed({
                    justOut()
                }, 1000L)
            }
        }
        binding.txtWa.setOnClickListener {
            //Open WhatsApp
            var formattedNumber: String = "62" +
                    binding.txtWa.text.toString().substring(1, binding.txtWa.text.toString().length)
            formattedNumber = formattedNumber.replace(" ","")

            try {
                val whatsappIntent = Intent(Intent.ACTION_SEND)
                whatsappIntent.type = "text/plain"
                whatsappIntent.setPackage("com.whatsapp")
                whatsappIntent.putExtra("jid", "$formattedNumber@s.whatsapp.net")
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share")
                requireActivity().startActivity(whatsappIntent)
            } catch (e: Exception) {
                Toast.makeText(activity, "Error/n$e", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtMail.setOnClickListener {
            //Open Email Apps
            try {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")

                intent.putExtra(Intent.EXTRA_EMAIL, (it as TextView).text.toString())
                intent.putExtra(Intent.EXTRA_SUBJECT, "")
                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Toast.makeText(activity, "Error/n$e", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        blurView()

        binding.btnCancel.setOnClickListener {

            Looper.myLooper()?.let { it1 ->
                Handler(it1).postDelayed({
                    justOut()
                }, 1000L)
            }
        }

        binding.txtWa.setOnClickListener {
            //Open WhatsApp
            var formattedNumber: String = "62" +
                    binding.txtWa.text.toString().substring(1, binding.txtWa.text.toString().length)
            formattedNumber = formattedNumber.replace(" ","")

            try {
                val whatsappIntent = Intent(Intent.ACTION_SEND)
                whatsappIntent.type = "text/plain"
                whatsappIntent.setPackage("com.whatsapp")
                whatsappIntent.putExtra("jid", "$formattedNumber@s.whatsapp.net")
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share")
                requireActivity().startActivity(whatsappIntent)
            } catch (e: Exception) {
                Toast.makeText(activity, "Error/n$e", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtMail.setOnClickListener {
            //Open Email Apps
            try {
                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")

                intent.putExtra(Intent.EXTRA_EMAIL, (it as TextView).text.toString())
                intent.putExtra(Intent.EXTRA_SUBJECT, "")
                if (intent.resolveActivity(requireActivity().packageManager) != null) {
                    startActivity(intent)
                }
            } catch (e: Exception) {
                Toast.makeText(activity, "Error/n$e", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun blurView() {
        val radius = 15f
        val decorView : View = activity?.window!!.decorView
        val rootView = decorView.findViewById<View>(android.R.id.content) as ViewGroup
        val windowBackground = decorView.background
        binding.blurView.setupWith(rootView, RenderScriptBlur(requireContext()))
            .setFrameClearDrawable(windowBackground)
            .setBlurRadius(radius)
            .setBlurAutoUpdate(true)
    }
}