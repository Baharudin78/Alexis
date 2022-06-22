package com.alexis.shop.ui.menu.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alexis.shop.R
import com.alexis.shop.domain.model.menu.MenuModel
import com.alexis.shop.utils.gone
import com.alexis.shop.utils.visible

class MenuViewHolder (inflater: LayoutInflater, parent: ViewGroup) :
    RecyclerView.ViewHolder(inflater.inflate(R.layout.item_menu_main, parent, false)) {
    var title: TextView = itemView.findViewById(R.id.text_menu)
    var line: View = itemView.findViewById(R.id.sparate_line)
    var submenu: RecyclerView = itemView.findViewById(R.id.recycle)
    var visibleSub = false

    fun bind(context: Context, item: MenuModel) {
        val lSubmenu: ArrayList<String> = ArrayList()
        if (item.icon == 0) {
            line.visible()
            title.gone()
        } else {
            line.gone()
            title.setCompoundDrawablesWithIntrinsicBounds(item.icon, 0, 0, 0)
            title.text = item.title
        }
        if (item.title == "Clothings"){
            lSubmenu.add("Tops 60k")
            lSubmenu.add("Dresses 60k")
            lSubmenu.add("Knitwear 60k")
            lSubmenu.add("Skirt 60k")
            lSubmenu.add("Pants 80k")
            lSubmenu.add("Jeans 80k")
            lSubmenu.add("Blazer 80k")
            lSubmenu.add("Knitted Sweater 80k")
            lSubmenu.add("Sweatshirt 80k")
            lSubmenu.add("Jacket 80k")
        }

        submenu.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = SubMenuAdapter(lSubmenu) {}
        }
    }
}