package com.example.BookShop.ui.checkout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BookShop.R
import com.example.BookShop.databinding.FragmentCheckOutBinding
import com.example.BookShop.ui.adapter.BookAdapter
import com.example.BookShop.ui.main.cart.CartViewModel
import com.example.BookShop.ui.profile.ProfileViewModel
import com.example.BookShop.ui.profile.ProfileViewModelFactory
import com.example.BookShop.utils.FormatMoney
import com.example.BookShop.utils.LoadingProgressBar

class CheckOutFragment : Fragment() {

    companion object {
        fun newInstance() = CheckOutFragment()
    }

    private lateinit var viewModelCheckOut: CheckOutViewModel
    private lateinit var viewModelCart: CartViewModel
    private lateinit var viewModelProfile: ProfileViewModel
    private lateinit var loadingProgressBar: LoadingProgressBar
    private var binding: FragmentCheckOutBinding? = null
    private lateinit var adapter: BookAdapter
    private var formatMoney = FormatMoney()
    private var cartId = ""
    private var shippingId = 1
    private var shippingPrice = 50000.00

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCheckOutBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelCheckOut = ViewModelProvider(this)[CheckOutViewModel::class.java]
        viewModelCart = ViewModelProvider(this)[CartViewModel::class.java]
        viewModelProfile = ViewModelProvider(
            this,
            ProfileViewModelFactory(requireActivity().application)
        )[ProfileViewModel::class.java]
        loadingProgressBar = LoadingProgressBar(requireContext())
        loadingProgressBar.show()
        adapter = BookAdapter()
        initViewModel()
        viewModelCart.getCartId()
        viewModelCart.getAllCartItem()
        viewModelProfile.getCustomer()

        binding?.apply {
            textPayment.setOnClickListener {
                val address = textCutomerAddress.text.toString()
                val receiverName = textCustomerName.text.toString()
                val receiverPhone = textCutomerPhone.text.toString()
//                viewModelCheckOut.createOrder(
//                    cartId,
//                    shippingId,
//                    address,
//                    receiverName,
//                    receiverPhone
//                )
                AlertDialog.Builder(requireContext())
                    .setMessage("hello")
                    .setCancelable(false)
                    .setPositiveButton("Close") { dialog, _ ->
//                    if(it.isState) {
//                        parentFragmentManager.popBackStack()
//                        dialog.cancel()
//                    }else{
//                        dialog.cancel()
//                    }
                        parentFragmentManager.popBackStack()
                        dialog.cancel()
                    }
                    .show()
//                loadingProgressBar.show()
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()

            }
            recyclerCartItem.layoutManager = LinearLayoutManager(context)
            recyclerCartItem.adapter = adapter
        }
    }

    private fun initViewModel() {
        viewModelCheckOut.message.observe(viewLifecycleOwner) {
            loadingProgressBar.cancel()
            AlertDialog.Builder(requireContext())
                .setMessage(it.message.message.capitalize())
                .setCancelable(false)
                .setPositiveButton("Close") { dialog, _ ->
//                    if(it.isState) {
//                        parentFragmentManager.popBackStack()
//                        dialog.cancel()
//                    }else{
//                        dialog.cancel()
//                    }
                    parentFragmentManager.popBackStack()
                    dialog.cancel()
                }
                .show()
        }
        viewModelCart.cartItem.observe(viewLifecycleOwner) { cartItem ->
            loadingProgressBar.cancel()
            binding?.apply {
                adapter.setDataCart(cartItem)
                textTotalPriceProduct.text =
                    formatMoney.formatMoney(adapter.getTotalPrice().toLong())
                textPromotionPrice.text =
                    formatMoney.formatMoney(adapter.getTotalPromotionPrice().toLong())
                getTotalPrice()
            }
        }
        viewModelCart.cartId.observe(viewLifecycleOwner) {
            cartId = it
        }
        viewModelProfile.profile.observe(viewLifecycleOwner) { customer ->
            loadingProgressBar.cancel()
            binding?.apply {
                textCustomerName.text = customer.name
                textCutomerPhone.text = customer.mobPhone
                textCutomerAddress.text = customer.address
            }
        }
    }

    fun getTotalPrice() {
        binding?.apply {
            textShipPrice.text =
                formatMoney.formatMoney(shippingPrice.toLong())
            var totalPrice = textTotalPriceProduct.text.replace(Regex("[^\\d]"), "").toInt() +
                    shippingPrice.toInt() - textPromotionPrice.text
                .replace(Regex("[^\\d]"), "").toInt()
            textTotalPrice.text = formatMoney.formatMoney(totalPrice.toLong())
            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when (checkedId) {
                    R.id.radiobtn_express -> {
                        shippingId = 1
                        shippingPrice = 50000.00
                    }
                    R.id.radiobtn_fast -> {
                        shippingId = 2
                        shippingPrice = 30000.00
                    }
                    R.id.radiobtn_economical -> {
                        shippingId = 3
                        shippingPrice = 10000.00
                    }
                }
                textShipPrice.text =
                    formatMoney.formatMoney(shippingPrice.toLong())
                totalPrice = textTotalPriceProduct.text.replace(Regex("[^\\d]"), "").toInt() +
                        shippingPrice.toInt() - textPromotionPrice.text
                    .replace(Regex("[^\\d]"), "").toInt()
                textTotalPrice.text = formatMoney.formatMoney(totalPrice.toLong())
            }
        }
    }
}