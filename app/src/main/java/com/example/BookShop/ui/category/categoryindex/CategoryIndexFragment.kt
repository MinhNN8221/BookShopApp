package com.example.BookShop.ui.category.categoryindex

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.BookShop.R
import com.example.BookShop.databinding.FragmentCategoryIndexBinding
import com.example.BookShop.ui.adapter.CategoryIndexAdapter
import com.example.BookShop.ui.adapter.OnItemClickListener
import com.example.BookShop.ui.category.CategoryBookFragment
import com.example.BookShop.utils.MySpanLookSize

class CategoryIndexFragment : Fragment() {

    private lateinit var binding: FragmentCategoryIndexBinding
    private lateinit var adapter: CategoryIndexAdapter

    companion object {
        fun newInstance() = CategoryIndexFragment()
    }

    private lateinit var viewModel: CategoryIndexViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoryIndexViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCategoryIndexBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CategoryIndexAdapter(false)
        val layoutManager = GridLayoutManager(context, 2)
        viewModel.categoryAllList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.setDataCategory(it)
            }
            binding.loadingLayout.root.visibility = View.INVISIBLE
        })
        viewModel.getAllCategory()
        navToProductInCategory()
        layoutManager.spanSizeLookup = MySpanLookSize(adapter, 1, 2)
        binding.recyclerCategory.adapter = adapter
        binding.recyclerCategory.layoutManager = layoutManager
        binding.imageLeft.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    private fun navToProductInCategory() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val bundle = Bundle()
                val categoryId = adapter.getCategory(position).categoryId
                val categoryName = adapter.getCategory(position).name
                bundle.putString("categoryId", categoryId.toString())
                bundle.putString("categoryName", categoryName)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, CategoryBookFragment().apply { arguments = bundle })
                    .addToBackStack("CategoryIndex")
                    .commit()
            }
        })
    }
}