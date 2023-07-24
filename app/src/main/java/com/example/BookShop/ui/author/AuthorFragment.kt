package com.example.BookShop.ui.author

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.example.BookShop.R
import com.example.BookShop.ui.adapter.BookAdapter
import com.example.BookShop.databinding.FragmentAuthorBinding
import com.example.BookShop.ui.adapter.OnItemClickListener
import com.example.BookShop.ui.productdetail.ProductdetailFragment

class AuthorFragment : Fragment() {

    companion object {
        fun newInstance() = AuthorFragment()
    }

    private lateinit var viewModel: AuthorViewModel
    private var binding: FragmentAuthorBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentAuthorBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AuthorViewModel::class.java)
        val bookList = viewModel.getProducts()
        val adapter = BookAdapter(bookList)
        binding?.apply {
            searchProduct.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    // task HERE
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (newText.isEmpty()) {
                        textAuthor.visibility = View.VISIBLE;
                    } else {
                        val layoutParams =
                            searchProduct.layoutParams as ViewGroup.MarginLayoutParams
                        val newMarginTopInDp = 12
                        val newMarginTopInPx = TypedValue.applyDimension(
                            TypedValue.COMPLEX_UNIT_DIP, newMarginTopInDp.toFloat(),
                            resources.displayMetrics
                        ).toInt()
                        layoutParams.topMargin = newMarginTopInPx
                        searchProduct.layoutParams = layoutParams
                        textAuthor.visibility = View.GONE;
                    }
                    return false;
                }
            })
            recyclerAuthor.layoutManager = GridLayoutManager(context, 2)
            recyclerAuthor.adapter = adapter
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val productFragment = ProductdetailFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, productFragment)
                    .addToBackStack("AuthorFragment")
                    .commit()
            }
        })
    }
}