package com.example.BookShop.ui.main.wishlist

import android.graphics.Color
import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BookShop.R
import com.example.BookShop.databinding.FragmentWishlistBinding
import com.example.BookShop.ui.adapter.OnItemClickListener
import com.example.BookShop.ui.adapter.WishListAdapter
import com.example.BookShop.ui.profile.ProfileFragment
import com.example.BookShop.utils.FormatDate
import com.example.BookShop.utils.FormatMoney

class WishlistFragment : Fragment() {

    companion object {
        fun newInstance() = WishlistFragment()
    }

    private lateinit var viewModel: WishlistViewModel
    private lateinit var adapter: WishListAdapter
    private var binding: FragmentWishlistBinding? = null
    private val formatMoney = FormatMoney()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentWishlistBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[WishlistViewModel::class.java]
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = WishListAdapter()
        initViewModel()
        viewModel.getWishList(10, 1, 100)
        binding?.recyclerWishList?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerWishList?.adapter = adapter
        binding?.apply {
            swipeRefresh.setOnRefreshListener {
                Handler().postDelayed({
                    swipeRefresh.isRefreshing = false
                    loadData()
                }, 1000)
            }
                swipeRefresh.setColorSchemeColors(resources.getColor(R.color.teal_200))
            imageProfile.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment())
                    .addToBackStack("WishListFragment")
                    .commit()
            }
        }
    }

    private fun loadData() {
        viewModel.getWishList(10, 1, 100)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initViewModel() {
        viewModel.wishList.observe(viewLifecycleOwner) {
            it.wishlist.let { WishList ->
                adapter.setData(WishList)
                addItemToCart()
                binding?.textPrice?.text = formatMoney.formatMoney(adapter.getTotalPrice().toLong())
            }
        }
    }
    private fun  addItemToCart(){
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = adapter.getBook(position)
                viewModel.addItemToCart(product.product_id)
                Toast.makeText(
                    context, "ADD ITEM TO CART SUCCESSFUL", Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getWishList(10, 1, 100)
    }
}