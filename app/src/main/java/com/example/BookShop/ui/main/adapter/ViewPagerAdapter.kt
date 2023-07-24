package com.example.BookShop.ui.main.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.BookShop.ui.main.home.HomeFragment
import com.example.BookShop.ui.main.search.SearchFragment
import com.example.BookShop.ui.main.shoppingbag.ShoppingbagFragment
import com.example.BookShop.ui.main.wishlist.WishlistFragment

class ViewPagerAdapter(fm: FragmentManager, behavior: Int) : FragmentStatePagerAdapter(fm, behavior) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragment()
            1 -> SearchFragment()
            2 -> WishlistFragment()
            3 -> ShoppingbagFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }

    override fun getCount(): Int {
        return 4
    }
}