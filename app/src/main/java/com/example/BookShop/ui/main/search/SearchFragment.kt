package com.example.BookShop.ui.main.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.BookShop.R
import com.example.BookShop.data.model.Product
import com.example.BookShop.databinding.FragmentSearchBinding
import com.example.BookShop.ui.adapter.BookAdapter
import com.example.BookShop.ui.adapter.ItemSpacingDecoration
import com.example.BookShop.ui.adapter.OnItemClickListener
import com.example.BookShop.ui.productdetail.ProductdetailFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchFragment : Fragment() {
    private var binding: FragmentSearchBinding? = null
    private lateinit var viewModel: SearchViewModel
    private lateinit var adapter: BookAdapter
    private var bookList = mutableListOf<Product>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // TODO: Use the ViewModel
//        bookList = viewModel.getProducts()
        adapter = BookAdapter(bookList)
        viewModel.getAllProducts()
        observeProducts()
        val horizontalSpacing =
            resources.getDimensionPixelSize(R.dimen.horizontal_spacing)
        val verticalSpacing =
            resources.getDimensionPixelSize(R.dimen.vertical_spacing)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.VISIBLE
        binding?.apply {
            recyclerProduct.layoutManager = GridLayoutManager(context, 2)
            recyclerProduct.adapter = adapter
            recyclerProduct.addItemDecoration(
                ItemSpacingDecoration(
                    horizontalSpacing,
                    verticalSpacing
                )
            )
            editSearch.setOnFocusChangeListener { v, hasFocus ->
                if (hasFocus) {
                    groupHistorySearch.visibility = View.VISIBLE
                    groupSearch.visibility = View.INVISIBLE
//                    val historyList = viewModel.getProducts()
//                    val adapterHistory = HistorySeachAdapter(historyList)
//                    recyclerHistorySearch.layoutManager = LinearLayoutManager(context)
//                    recyclerHistorySearch.adapter = adapterHistory
                } else {
                }
            }

            editSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int,
                ) {
                }

                override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

                }

                override fun afterTextChanged(editable: Editable) {
                    val layoutParams = textTitleSearch.layoutParams
                    if (editSearch.text.isEmpty()) {
                        textTitleSearch.visibility = View.VISIBLE
                        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        textTitleSearch.layoutParams = layoutParams
                    } else {
                        textTitleSearch.visibility = View.INVISIBLE
                        layoutParams.height = 0
                        textTitleSearch.layoutParams = layoutParams
                    }
                }
            })
        }
    }

    private fun observeProducts() {
        viewModel.productList.observe(viewLifecycleOwner, Observer { productList ->
            if (productList != null) {
                adapter = BookAdapter(productList)
                binding?.recyclerProduct?.adapter = adapter
                navToProductDetail()
            } else {
                Log.d("NULLLL", "HEllo")
            }
        })
    }
    private fun navToProductDetail(){
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product=adapter.getBook(position)
                val bundle = Bundle()
                Log.d("PRODUCT_ID", product.product_id.toString())
                bundle.putString("book_id", product.product_id.toString())
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, ProductdetailFragment().apply { arguments = bundle })
                    .addToBackStack("SearchFragment")
                    .commit()
            }
        })
    }
}

