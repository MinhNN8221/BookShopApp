package com.example.BookShop.ui.main.wishlist

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.BookShop.R
import com.example.BookShop.databinding.FragmentWishlistBinding
import com.example.BookShop.ui.adapter.OnItemClickListener
import com.example.BookShop.ui.adapter.WishListAdapter
import com.example.BookShop.ui.profile.ProfileFragment
import com.example.BookShop.utils.AlertMessageViewer
import com.example.BookShop.utils.FormatMoney

class WishlistFragment : Fragment() {

    companion object {
        fun newInstance() = WishlistFragment()
    }

    private lateinit var viewModel: WishlistViewModel
    private lateinit var adapter: WishListAdapter
    private var binding: FragmentWishlistBinding? = null
    private val formatMoney = FormatMoney()
    private var currentPage = 1
    private var lastPosition = 0
    private var totalPosition = 0
    private var currentPosition = 0

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
        viewModel.getWishList(10, currentPage, 100)
        binding?.apply {
            swipeRefresh.setOnRefreshListener {
                Handler().postDelayed({
                    swipeRefresh.isRefreshing = false
                    refreshData()
                }, 1000)
            }
            swipeRefresh.setColorSchemeColors(resources.getColor(R.color.teal_200))
            imageProfile.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment())
                    .addToBackStack("WishListFragment")
                    .commit()
            }
            textAddToBag.setOnClickListener {
//                viewModel.addAllItemToCart()
                Toast.makeText(
                    requireContext(),
                    "Missing API to add wishlist products to cart!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            recyclerWishList.layoutManager = LinearLayoutManager(context)
            recyclerWishList.adapter = adapter
        }
        handleLoadData()
    }

    private fun handleLoadData() {
        binding?.apply {
            recyclerWishList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    lastPosition =
                        (recyclerWishList.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    totalPosition = adapter.itemCount
                    if (lastPosition != currentPosition && lastPosition == totalPosition - 3) {
                        currentPage++
                        viewModel.getWishList(10, currentPage, 100)
                        currentPosition = lastPosition
                    }
                }
            })
        }
    }

    private fun refreshData() {
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
        viewModel.message.observe(viewLifecycleOwner) {
            AlertMessageViewer.showAlertDialogMessage(requireContext(), it.message)
        }
    }

    private fun addItemToCart() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val product = adapter.getBook(position)
                viewModel.addItemToCart(product.product_id)
                Toast.makeText(
                    context, "Add item to cart successful", Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        viewModel.getWishList(10, 1, 100)
    }
}