package com.example.shopbook

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.shopbook.databinding.FragmentMainMenuBinding
import com.example.shopbook.ui.main.home.HomeFragment
import com.example.shopbook.ui.main.search.SearchFragment
import com.example.shopbook.ui.main.shoppingbag.ShoppingbagFragment
import com.example.shopbook.ui.main.wishlist.WishlistFragment
import com.example.shopbook.ui.productdetail.ProductdetailFragment


class MainActivity : AppCompatActivity() {
    private lateinit var bnd: FragmentMainMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bnd = FragmentMainMenuBinding.inflate(layoutInflater)
        val view: View = bnd.getRoot()
        setContentView(view)
        loadFragment(HomeFragment())
        bnd.navigation.setOnItemSelectedListener {
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
                    loadFragment(ProductdetailFragment())
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
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()
    }
}