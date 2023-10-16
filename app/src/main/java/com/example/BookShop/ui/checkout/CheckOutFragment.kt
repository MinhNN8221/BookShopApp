package com.example.BookShop.ui.checkout

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BookShop.R
import com.example.BookShop.data.api.apizalopay.CreateOrder
import com.example.BookShop.databinding.FragmentCheckOutBinding
import com.example.BookShop.databinding.FragmentMainMenuBinding
import com.example.BookShop.ui.adapter.BookListAdapter
import com.example.BookShop.ui.adapter.ViewPager2Adapter
import com.example.BookShop.ui.main.MainMenuFragment
import com.example.BookShop.ui.main.cart.CartFragment
import com.example.BookShop.ui.main.cart.CartViewModel
import com.example.BookShop.ui.main.home.HomeFragment
import com.example.BookShop.ui.main.search.SearchFragment
import com.example.BookShop.ui.main.wishlist.WishlistFragment
import com.example.BookShop.ui.order.orderinfor.OrderInforFragment
import com.example.BookShop.ui.profile.ProfileViewModel
import com.example.BookShop.ui.profile.ProfileViewModelFactory
import com.example.BookShop.utils.AlertMessageViewer
import com.example.BookShop.utils.FormatMoney
import com.example.BookShop.utils.LoadingProgressBar
import vn.zalopay.sdk.Environment
import vn.zalopay.sdk.ZaloPayError
import vn.zalopay.sdk.ZaloPaySDK
import vn.zalopay.sdk.listeners.PayOrderListener

class CheckOutFragment : Fragment() {

    companion object {
        fun newInstance() = CheckOutFragment()
    }

    private lateinit var viewModelCheckOut: CheckOutViewModel
    private lateinit var viewModelCart: CartViewModel
    private lateinit var viewModelProfile: ProfileViewModel
    private lateinit var loadingProgressBar: LoadingProgressBar
    private var binding: FragmentCheckOutBinding? = null
    private lateinit var adapter: BookListAdapter
    private var formatMoney = FormatMoney()
    private var cartId = ""
    private var check = false
    private var shippingId = 1
    private var shippingPrice = 50000.00

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        // ZaloPay SDK Init
        ZaloPaySDK.init(2553, Environment.SANDBOX)
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
        adapter = BookListAdapter()
        initViewModel()
        viewModelCart.getCartId()
        viewModelCart.getAllCartItem()
        viewModelProfile.getCustomer()

        val items =
            arrayOf("Thanh toán bằng MoMo", "Thanh toán bằng ZaloPay", "Thanh toán bằng tiền mặt")

        // Kết nối ArrayAdapter với Spinner
        val adapterSpinner = ArrayAdapter(requireContext(), R.layout.item_spinner, items)
        var idPayment = 0L
        binding?.apply {
            spinnerPayment.adapter = adapterSpinner
            spinnerPayment.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    idPayment = id
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
            textChangeInfor.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, OrderInforFragment())
                    .addToBackStack("CheckOut")
                    .commit()
            }
            textPayment.setOnClickListener {
//                val address = textCutomerAddress.text.toString()
//                val receiverName = textCustomerName.text.toString()
//                val receiverPhone = textCutomerPhone.text.toString()
//                viewModelCheckOut.createOrder(
//                    cartId,
//                    shippingId,
//                    address,
//                    receiverName,
//                    receiverPhone
//                )
                when (idPayment) {
                    1L -> paymentZalopay()
                    2L -> paymentCash()
                }
                check = true
//                loadingProgressBar.show()
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()

            }
            recyclerCartItem.layoutManager = LinearLayoutManager(context)
            recyclerCartItem.adapter = adapter
        }
    }

    private fun paymentZalopay() {
        var token = ""
        val orderApi = CreateOrder()
        try {
            val amount = binding?.textTotalPrice?.text?.replace(Regex("\\D"), "")
            val data = orderApi.createOrder(amount.toString())
            val code = data?.getString("return_code")
            Toast.makeText(requireContext(), "return_code: $code", Toast.LENGTH_LONG).show()
            if (code == "1") {
                token = data.getString("zp_trans_token")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        //Gọi hàm thanh toán
        ZaloPaySDK.getInstance()
            .payOrder(requireActivity(), token, "zalopay_payment://app", object : PayOrderListener {
                override fun onPaymentSucceeded(
                    transactionId: String,
                    transToken: String,
                    appTransID: String,
                ) {
                    createOrder()
                    activity?.runOnUiThread {
                        AlertMessageViewer.showAlertZalopay(
                            requireContext(),
                            "Thanh toán thành công!",
                            "TransactionId: $transactionId - TransToken: $transToken"
                        )
                    }
                }

                override fun onPaymentCanceled(zpTransToken: String, appTransID: String) {
                    AlertMessageViewer.showAlertZalopay(
                        requireContext(),
                        "Khách hàng hủy thanh toán bằng ZaloPay",
                        "zpTransToken: $zpTransToken"
                    )
                }

                override fun onPaymentError(
                    zaloPayError: ZaloPayError,
                    zpTransToken: String,
                    appTransID: String,
                ) {
                    AlertMessageViewer.showAlertZalopay(
                        requireContext(),
                        "Thanh toán thất bại!",
                        "ZaloPayErrorCode: ${zaloPayError.toString()}\nTransToken: $zpTransToken"
                    )
                }
            })

    }

    //Cần bắt sự kiện OnNewIntent vì ZaloPay App sẽ gọi deeplink về app của Merchant
    fun handleNewIntent(intent: Intent) {
        ZaloPaySDK.getInstance().onResult(intent)
    }

    private fun paymentCash() {
        createOrder()
    }

    private fun createOrder() {
        binding?.apply {
            val address = textCutomerAddress.text.toString()
            val receiverName = textCustomerName.text.toString()
            val receiverPhone = textCutomerPhone.text.toString()
            viewModelCheckOut.createOrder(
                cartId,
                shippingId,
                address,
                receiverName,
                receiverPhone
            )
        }
    }

    private fun initViewModel() {
        viewModelCheckOut.message.observe(viewLifecycleOwner) {
            loadingProgressBar.cancel()
            if (check) {
                AlertDialog.Builder(requireContext())
                    .setMessage(it.message.message.capitalize())
                    .setCancelable(false)
                    .setPositiveButton("Close") { dialog, _ ->
                        if (it.isState) {
                            parentFragmentManager.popBackStack()
                            dialog.cancel()
                        } else {
                            dialog.cancel()
                        }
                    }
                    .show()
                check = false
            }
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