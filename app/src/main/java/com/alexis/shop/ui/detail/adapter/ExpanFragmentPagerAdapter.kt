package com.alexis.shop.ui.detail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * Created by Uwais Alqadri on July 07, 2021
 */
class ExpanFragmentPagerAdapter(
	fragmentActivity: FragmentActivity,
	private val fragments: ArrayList<Fragment>
) : FragmentStateAdapter(fragmentActivity) {

	override fun getItemCount(): Int = fragments.size

	override fun createFragment(position: Int): Fragment {
		return fragments[position]
	}
}