package com.example.BookShop.ui.order.orderhistory

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.BookShop.R
import com.example.BookShop.data.model.Order
import com.example.BookShop.data.model.OrderHistory
import com.example.BookShop.databinding.FragmentOrderHistoryBinding
import com.example.BookShop.ui.adapter.OnItemClickListener
import com.example.BookShop.ui.adapter.OrderHistoryAdapter
import com.example.BookShop.ui.order.orderdetail.OrderDetailFragment
import com.example.BookShop.utils.FormatDate
import java.time.LocalDateTime

class OrderHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = OrderHistoryFragment()
    }

    private var binding: FragmentOrderHistoryBinding? = null
    private lateinit var viewModel: OrderHistoryViewModel
    private lateinit var adapter: OrderHistoryAdapter
    private var formatDate = FormatDate()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOrderHistoryBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(OrderHistoryViewModel::class.java)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = OrderHistoryAdapter()
        binding?.loadingLayout?.root?.visibility = View.VISIBLE
        val list = mutableListOf<OrderHistory>()
        val currentDate = formatDate.formatDate(LocalDateTime.now().toString())
        val mapOrder: MutableMap<String, MutableList<Order>> = mutableMapOf()
        viewModel.orderHistory.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                mapOrder.clear()
                for (order in it) {
                    val date = formatDate.formatDate(order.createdOn)
                    if (date == currentDate) {
                        mapOrder.computeIfAbsent("Hôm nay") { mutableListOf() }.add(order)
                    } else {
                        mapOrder.computeIfAbsent(date) { mutableListOf() }.add(order)
                    }
                }
                list.clear()
                for ((key, value) in mapOrder) {
                    list.add(OrderHistory(key, null))
                    for (values in value) {
                        list.add(OrderHistory(null, values))
                    }
                }
                if (list.isEmpty()) {
                    binding?.textOrderHistory?.visibility = View.VISIBLE
                }
                adapter.setData(list)
                navToOrderDetail()
                binding?.loadingLayout?.root?.visibility = View.INVISIBLE
            }
        })
        viewModel.getOrderHistory()
        binding?.recyclerOrderHistory?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerOrderHistory?.adapter = adapter
        binding?.apply {
            imageLeftOrder.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun navToOrderDetail() {
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val orderId = adapter.getOrder(position)?.orderId
                val orderStatus = adapter.getOrder(position)?.orderStatus
                val bundle = Bundle()
                bundle.putString("orderId", orderId.toString())
                bundle.putString("orderStatus", orderStatus)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, OrderDetailFragment().apply { arguments = bundle })
                    .addToBackStack("Orderhistory")
                    .commit()
            }

        })
    }
}