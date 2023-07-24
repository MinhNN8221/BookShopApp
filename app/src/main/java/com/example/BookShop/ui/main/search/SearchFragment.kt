package com.example.BookShop.ui.main.search

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BookShop.R
import com.example.BookShop.ui.adapter.BookAdapter
import com.example.BookShop.databinding.FragmentSearchBinding
import com.example.BookShop.ui.adapter.HistorySeachAdapter
import com.example.BookShop.ui.adapter.OnItemClickListener
import com.example.BookShop.ui.productdetail.ProductdetailFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        // TODO: Use the ViewModel
        val bookList = viewModel.getProducts()
        val adapter = BookAdapter(bookList)

        binding?.recyclerProduct?.layoutManager = GridLayoutManager(context, 2)
        binding?.recyclerProduct?.adapter = adapter
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, ProductdetailFragment())
                    .addToBackStack("SearchFragment")
                    .commit()
            }
        })
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.VISIBLE
        binding?.apply {
            editSearch.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    constraintFilter.visibility = View.INVISIBLE
                    recyclerProduct.visibility = View.INVISIBLE
                    textTitleSearch.visibility = View.VISIBLE
                    recyclerHistorySearch.visibility = View.VISIBLE
                    val historyList=viewModel.getProducts()
                    val adapterHistory = HistorySeachAdapter(historyList)
                    recyclerHistorySearch.layoutManager=LinearLayoutManager(context)
                    recyclerHistorySearch.adapter=adapterHistory
                } else {
                    textTitleSearch.visibility = View.INVISIBLE
                }
            }
        }
    }
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//    }
}