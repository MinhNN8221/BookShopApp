package com.example.BookShop.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.BookShop.R
import com.example.BookShop.databinding.FragmentMainMenuBinding
import com.example.BookShop.ui.main.home.HomeFragment
import com.example.BookShop.ui.main.search.SearchFragment
import com.example.BookShop.ui.main.shoppingbag.ShoppingbagFragment
import com.example.BookShop.ui.main.wishlist.WishlistFragment

class MainMenuFragment : Fragment() {
    private lateinit var binding: FragmentMainMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainMenuBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        loadFragment(HomeFragment())
        binding.navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.menu_search -> {
                    loadFragment(SearchFragment())
                    true
                }
                R.id.menu_wishlist -> {
                    loadFragment(WishlistFragment())
                    true
                }
                else -> {
                    loadFragment(ShoppingbagFragment())
                    true
                }
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }
}