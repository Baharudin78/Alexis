package com.alexis.shop.ui.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.faq.FAQModel
import com.alexis.shop.ui.menu.adapter.FAQAdapter
import com.alexis.shop.utils.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FAQFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var title: TextView
    lateinit var btnCancel: ImageView
    lateinit var btnBack: ImageView
    lateinit var recyclerView: RecyclerView

    private var listFaq: ArrayList<FAQModel> = ArrayList()
    lateinit var adapterFaq: FAQAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleBackPressed()
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_frequenly_questions, container, false)
        title = root.findViewById(R.id.txt_wl)
        btnCancel = root.findViewById(R.id.btn_cancel)
        btnBack = root.findViewById(R.id.btn_back)
        recyclerView = root.findViewById(R.id.recycle_order)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title.text = param1
        btnBack.setOnClickListener {
//            val fragment =
            //Control back button action
//            when(param2){
//                "DetailOrder" ->  DetailOrderFragment.newInstance("","")
//                else ->  HelpCenterFragment.newInstance("","")
//            }
            requireActivity().supportFragmentManager.popBackStack()
        }

        btnCancel.setOnClickListener {
            justOut()
        }

        //Sample list FAQ title only
        listFaq.add(FAQModel("26 Jan 2021", false))
        listFaq.add(FAQModel("17 Nov 2020", false))
        listFaq.add(FAQModel("02 Okt 2020", false))
        listFaq.add(FAQModel("30 Sep 2020", false))
        listFaq.add(FAQModel("08 Agu 2020", false))
        listFaq.add(FAQModel("20 Jul 2020", false))

        adapterFaq = FAQAdapter(requireContext(), listFaq) {
            changeChoosenFAQ(it)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = adapterFaq
        }
    }

    private fun changeChoosenFAQ(bool: FAQModel) {
        //Action when FAQ is open
        val faqs = ArrayList<FAQModel>()
        listFaq.forEach { faq ->
            if(faq.getData() == bool.getData()){
                faq.setChoosed(!bool.getChoosed())
            }else{
                faq.setChoosed(false)
            }
            faqs.add(faq)
        }
        listFaq.clear()
        listFaq.addAll(faqs)
        adapterFaq.notifyDataSetChanged()
    }
    
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FAQFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}