package com.example.BookShop.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.BookShop.R
import com.example.BookShop.databinding.FragmentHomeBinding
import com.example.BookShop.ui.category.categoryindex.CategoryIndexFragment
import com.example.BookShop.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            imageProfile.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment())
                    .addToBackStack("HomeFragment")
                    .commit()
            }
            imageNavCategory.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, CategoryIndexFragment())
                    .addToBackStack("HomeFragment")
                    .commit()
            }
        }
    }
}