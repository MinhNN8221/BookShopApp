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
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.VISIBLE
        binding?.apply {
            imageProfile.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, ProfileFragment())
                    .addToBackStack("HomeFragment")
                    .commit()
            }
            imageNavCategory.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, CategoryIndexFragment())
                    .addToBackStack("HomeFragment")
                    .commit()
            }
        }
    }
}