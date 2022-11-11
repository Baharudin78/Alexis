package com.alexis.shop.ui.menu.contactus

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexis.shop.BaseFragment
import com.alexis.shop.R
import com.alexis.shop.data.Resource
import com.alexis.shop.databinding.FragmentContactUsBinding
import com.alexis.shop.domain.model.contact.ContactDataModel
import com.alexis.shop.ui.menu.adapter.contact.ContactAdapter
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations
import com.alexis.shop.utils.handleBackPressed
import com.alexis.shop.utils.justOut
import dagger.hilt.android.AndroidEntryPoint

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

@AndroidEntryPoint
class ContactUsFragment : BaseFragment<FragmentContactUsBinding>(), OnClickItem {
    private var param1: String? = null
    private var param2: String? = null

//    lateinit var cancel_button: ImageView
//    lateinit var contact_info: TextView
//    lateinit var contact_mail: TextView
//    lateinit var textTitle: TextView
//    lateinit var contact_wa: TextView

    private val viewModel : ContactViewModel by viewModels()
    private lateinit var contactAdapter : ContactAdapter
    private var contactDataModel = ArrayList<ContactDataModel>()

    override fun getViewBinding() =  FragmentContactUsBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun main() {
        super.main()
        contactAdapter = ContactAdapter(binding.root.context, this)
        with(binding.recycleListmenu) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = contactAdapter
        }
        getContact()
    }

    private fun getContact() {
        viewModel.getContact().observe(viewLifecycleOwner){response ->
            if (response != null) {
                when(response) {
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        val contactValue = response.data?.items as ArrayList<ContactDataModel>
                        contactDataModel = contactValue
                        contactAdapter.setDataContact(contactValue)
                    }
                    is Resource.Error -> {
                        Toast.makeText(binding.root.context, "Failed Get Helpcenter", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.fragment_contact_us, container, false)
//        contact_info = root.findViewById(R.id.contact_info)
//        contact_mail = root.findViewById(R.id.txt_mail)
//        contact_wa = root.findViewById(R.id.txt_wa)
//        cancel_button = root.findViewById(R.id.btn_cancel)
//        this.textTitle = root.findViewById(R.id.textTitle)
//
//        return root
//    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        Animations.runAnimation(
//            requireActivity(),
//            Animations.ANIMATION_IN,
//            null,
//            this.contact_info,
//            this.contact_mail,
//            this.contact_wa,
//            this.textTitle
//        )
//
//        cancel_button.setOnClickListener {
//
//            Animations.runAnimation(
//                requireActivity(),
//                Animations.ANIMATION_OUT,
//                null,
//                this.contact_info,
//                this.contact_mail,
//                this.contact_wa,
//                this.textTitle
//            )
//
//            Handler(Looper.myLooper()!!).postDelayed({
//                justOut()
//            }, 1000L)
//        }
//
//        contact_info.text = "Monday to Friday from 09:00 to 24:00. \nWeekend & Holiday from 10:00 to 21:00."
//
//        contact_wa.setOnClickListener {
//            //Open WhatsApp
//            var formattedNumber: String = "62" +
//                    contact_wa.text.toString().substring(1, contact_wa.text.toString().length)
//            formattedNumber = formattedNumber.replace(" ","")
//
//            try {
//                val whatsappIntent = Intent(Intent.ACTION_SEND)
//                whatsappIntent.type = "text/plain"
//                whatsappIntent.setPackage("com.whatsapp")
//                whatsappIntent.putExtra("jid", "$formattedNumber@s.whatsapp.net")
//                whatsappIntent.putExtra(Intent.EXTRA_TEXT, "The text you wanted to share")
//                requireActivity().startActivity(whatsappIntent)
//            } catch (e: Exception) {
//                Toast.makeText(activity, "Error/n$e", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        contact_mail.setOnClickListener {
//            //Open Email Apps
//            try {
//                val intent = Intent(Intent.ACTION_SENDTO)
//                intent.data = Uri.parse("mailto:")
//
//                intent.putExtra(Intent.EXTRA_EMAIL, (it as TextView).text.toString())
//                intent.putExtra(Intent.EXTRA_SUBJECT, "")
//                if (intent.resolveActivity(requireActivity().packageManager) != null) {
//                    startActivity(intent)
//                }
//            } catch (e: Exception) {
//                Toast.makeText(activity, "Error/n$e", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ContactUsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onClick(item: Any) {
        Toast.makeText(requireContext(), "clicked", Toast.LENGTH_SHORT).show()
    }
}