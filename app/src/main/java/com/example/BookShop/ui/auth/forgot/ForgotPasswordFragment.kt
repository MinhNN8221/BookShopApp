package com.example.BookShop.ui.auth.forgot

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.BookShop.databinding.FragmentForgotPasswordBinding
import com.example.BookShop.ui.auth.forgot.viewmodel.ForgotPasswordViewModel

class ForgotPasswordFragment : Fragment() {

    companion object {
        fun newInstance() = ForgotPasswordFragment()
    }

    private lateinit var viewModel: ForgotPasswordViewModel
    private var binding:FragmentForgotPasswordBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentForgotPasswordBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ForgotPasswordViewModel::class.java]
        // TODO: Use the ViewModel
        binding?.imageLeft?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

}