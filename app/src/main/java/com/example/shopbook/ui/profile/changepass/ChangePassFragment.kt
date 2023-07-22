package com.example.shopbook.ui.profile.changepass

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shopbook.R
import com.example.shopbook.databinding.FragmentChangePassBinding

class ChangePassFragment : Fragment() {

    companion object {
        fun newInstance() = ChangePassFragment()
    }

    private lateinit var viewModel: ChangePassViewModel
    private var binding:FragmentChangePassBinding?=null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentChangePassBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChangePassViewModel::class.java)
        // TODO: Use the ViewModel
        binding?.imageLeft?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

}