package com.example.BookShop.ui.profile.updateprofile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.BookShop.R
import com.example.BookShop.databinding.FragmentUpdateProfileBinding
import com.example.BookShop.ui.profile.permission.PermissionFragment

class UpdateProfileFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateProfileFragment()
    }

    private var binding:FragmentUpdateProfileBinding?=null
    private lateinit var viewModel: UpdateProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentUpdateProfileBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.imageLeft?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding?.cardview?.setOnClickListener {
            val fragmentPermission=PermissionFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragmentPermission)
                .addToBackStack("updateProfile")
                .commit()
        }
        binding?.imageLeft?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdateProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }

}