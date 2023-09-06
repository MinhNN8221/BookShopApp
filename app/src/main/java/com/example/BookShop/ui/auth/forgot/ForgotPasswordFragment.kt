package com.example.BookShop.ui.auth.forgot

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.BookShop.R
import com.example.BookShop.data.model.AuthResponse
import com.example.BookShop.data.model.Customer
import com.example.BookShop.databinding.FragmentForgotPasswordBinding
import com.example.BookShop.ui.auth.signin.SignInFragment
import com.example.BookShop.utils.AlertMessageViewer
import com.example.BookShop.utils.LoadingProgressBar

class ForgotPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ForgotPasswordFragment()
    }

    private lateinit var viewModel: ForgotPasswordViewModel
    private var binding: FragmentForgotPasswordBinding? = null
    private lateinit var loadingProgressBar: LoadingProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ForgotPasswordViewModel::class.java]
        loadingProgressBar=LoadingProgressBar(requireContext())
        initViewModel()
        binding?.apply {
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            buttonSendCode.setOnClickListener {
                val email = editMail.text.toString()
                val auth = AuthResponse(customer = Customer(email = email))
                viewModel.checkFields(auth)
                loadingProgressBar.show()
            }
            textLogin.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignInFragment())
                    .commit()
            }
        }
    }

    private fun initViewModel() {
        viewModel.message.observe(viewLifecycleOwner) { message ->
            loadingProgressBar.cancel()
            message?.let { message ->
                AlertMessageViewer.showAlertDialogMessage(requireContext(), message.message)
            }
        }
    }

}