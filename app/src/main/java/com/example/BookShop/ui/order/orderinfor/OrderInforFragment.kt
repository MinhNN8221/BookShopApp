package com.example.BookShop.ui.order.orderinfor

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.BookShop.R
import com.example.BookShop.data.model.AuthResponse
import com.example.BookShop.data.model.Customer
import com.example.BookShop.databinding.FragmentOrderInforBinding
import com.example.BookShop.ui.profile.updateprofile.UpdateProfileViewModel
import com.example.BookShop.utils.AlertMessageViewer
import com.example.BookShop.utils.LoadingProgressBar

class OrderInforFragment : Fragment() {

    companion object {
        fun newInstance() = OrderInforFragment()
    }

    private lateinit var viewModel: UpdateProfileViewModel
    private var binding: FragmentOrderInforBinding? = null
    private lateinit var loadingProgressBar: LoadingProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentOrderInforBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[UpdateProfileViewModel::class.java]
        loadingProgressBar=LoadingProgressBar(requireContext())
        loadingProgressBar.show()
        initViewModel()
        viewModel.getCustomer()
        binding?.apply {
            textUpdate.setOnClickListener {
                val name = editFullname.text.toString()
                val address = editAddress.text.toString()
                val mobPhone = editPhoneNumber.text.toString()
                val user = AuthResponse(
                    customer = Customer(
                        name = name,
                        address = address,
                        mobPhone = mobPhone
                    )
                )
                viewModel.checkFields(user)
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun initViewModel() {
        viewModel.message.observe(viewLifecycleOwner) {
            AlertMessageViewer.showAlertDialogMessage(requireContext(), it)
        }
        viewModel.profile.observe(viewLifecycleOwner) {
            it?.let {
                binding?.apply {
                    editFullname.setText(it.name)
                    editAddress.setText(it.address)
                    editPhoneNumber.setText(it.mobPhone)
                }
            }
            loadingProgressBar.cancel()
        }
    }
}