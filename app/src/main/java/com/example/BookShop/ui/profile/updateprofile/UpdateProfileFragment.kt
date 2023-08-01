package com.example.BookShop.ui.profile.updateprofile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.BookShop.R
import com.example.BookShop.data.model.Customer
import com.example.BookShop.databinding.FragmentUpdateProfileBinding
import com.example.BookShop.ui.profile.permission.PermissionFragment

class UpdateProfileFragment : Fragment() {
    companion object {
        fun newInstance() = UpdateProfileFragment()
    }

    private var binding: FragmentUpdateProfileBinding? = null
    private lateinit var viewModel: UpdateProfileViewModel
    private var customer_id: Int? = null
    private var avatar: String? = null
    private var shipping_region_id: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentUpdateProfileBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdateProfileViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.layoutLoading?.root?.visibility=View.VISIBLE
        viewModel.getCustomer()
        observeProfile()
        binding?.apply {
            cardview.setOnClickListener {
                val fragmentPermission = PermissionFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, fragmentPermission)
                    .addToBackStack("updateProfile")
                    .commit()
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            textUpdateProfile.setOnClickListener {
                updateProfie()
            }
        }
    }

    private fun observeProfile() {
        viewModel.profile.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                bindData(it)
                customer_id = it.customer_id
                shipping_region_id = it.shipping_region_id
                avatar = it.avatar
            }
        })
    }

    private fun bindData(profile: Customer) {
        binding?.apply {
            Glide.with(root)
                .load(profile.avatar)
                .centerCrop()
                .into(imageAvatar)
            editFullname.setText(profile.name)
            editDob.setText(profile.date_of_birth)
            editPhone.setText(profile.mob_phone)
            editAddress.setText(profile.address)
            editEmail.setText(profile.email)
            if (profile.gender.equals("Nam")) {
                radiobtnNam.isChecked = true
            } else {
                radiobtnNu.isChecked = true
            }
            binding?.layoutLoading?.root?.visibility=View.INVISIBLE
        }
    }

    private fun updateProfie() {
        binding?.apply {
            val fullName = editFullname.text.toString()
            val Dob = editDob.text.toString()
            var gender = "Nữ"
            if (radiobtnNam.isChecked) {
                gender = "Nam"
            }
            val phone = editPhone.text.toString()
            val address = editAddress.text.toString()
            viewModel.updateCustomer(fullName, address, Dob, gender, phone)
        }
    }
}