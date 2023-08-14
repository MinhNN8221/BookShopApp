package com.example.BookShop.ui.main.shoppingbag

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BookShop.R
import com.example.BookShop.databinding.FragmentShoppingBagBinding
import com.example.BookShop.ui.adapter.CartAdapter
import com.example.BookShop.ui.profile.ProfileFragment
import com.example.BookShop.utils.FormatMoney

class ShoppingbagFragment : Fragment() {

    companion object {
        fun newInstance() = ShoppingbagFragment()
    }

    private lateinit var viewModel: ShoppingbagViewModel
    private var binding: FragmentShoppingBagBinding? = null
    private lateinit var adapter: CartAdapter
    private var formatMoney = FormatMoney()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentShoppingBagBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[ShoppingbagViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = CartAdapter()
        initViewModel()
        viewModel.getAllCartItem()
        binding?.apply {
            swipeRefresh.setOnRefreshListener {
                Handler().postDelayed({
                    swipeRefresh.isRefreshing = false
                    viewModel.getAllCartItem()
                }, 1000)
            }
            swipeRefresh.setColorSchemeColors(resources.getColor(R.color.teal_200))
            imageProfile.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileFragment())
                    .addToBackStack("CartFragment")
                    .commit()
            }
            recyclerCartItem.layoutManager = LinearLayoutManager(context)
            recyclerCartItem.adapter = adapter
        }
    }

    private fun initViewModel() {
        viewModel.cartItem.observe(viewLifecycleOwner) { cartItem ->
            adapter.setData(cartItem)
            binding?.textPrice?.text = formatMoney.formatMoney(adapter.getTotalPrice().toLong())
        }
    }

}