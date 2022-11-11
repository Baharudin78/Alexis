package com.alexis.shop.ui.menu.adapter.contact

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.contact.ContactDataModel
import com.alexis.shop.utils.OnClickItem
import com.alexis.shop.utils.animation.Animations

class ContactAdapter(
    private val context : Context,
    private val listener : OnClickItem
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>(){

    private var contactList = ArrayList<ContactDataModel>()

    fun setDataContact(data : ArrayList<ContactDataModel>) {
        contactList.clear()
        contactList = data
        notifyDataSetChanged()
    }

    class ContactViewHolder(inflater : LayoutInflater, parent : ViewGroup) :
            RecyclerView.ViewHolder(inflater.inflate(R.layout.item_contact, parent, false)) {
                var contactText : TextView = itemView.findViewById(R.id.tv_no)
                var waText : TextView = itemView.findViewById(R.id.tv_wa)
                fun bind(item : ContactDataModel) {
                    contactText.text = item.email
                    waText.text = item.whatsapp
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ContactViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item : ContactDataModel = contactList[position]
        holder.bind(item)
        Animations.runAnimation(context, Animations.ANIMATION_IN, position, holder.itemView)
        holder.itemView.setOnClickListener {
            listener.onClick(item)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

}