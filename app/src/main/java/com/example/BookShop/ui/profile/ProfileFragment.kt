package com.example.BookShop.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.BookShop.R
import com.example.BookShop.data.model.Customer
import com.example.BookShop.databinding.FragmentProfileBinding
import com.example.BookShop.ui.order.orderhistory.OrderHistoryFragment
import com.example.BookShop.ui.profile.changepass.ChangePassFragment
import com.example.BookShop.ui.profile.profilesignin.ProfileSigninFragment
import com.example.BookShop.ui.profile.updateprofile.UpdateProfileFragment
import com.example.BookShop.utils.MySharedPreferences
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProfileFragment : Fragment() {
    private var binding: FragmentProfileBinding? = null

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, ProfileViewModelFactory(requireActivity().application))[ProfileViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.layoutLoading?.root?.visibility = View.VISIBLE
        var email = ""
        activity?.let { MySharedPreferences.init(it.applicationContext) }
        viewModel.profile.observe(viewLifecycleOwner, Observer {
            bindData(it)
            email = it.email.toString()
        })
        viewModel.getCustomer()
        binding?.apply {
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageUpdate.setOnClickListener {
                val fragmentUpdate = UpdateProfileFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentUpdate)
                    .addToBackStack("profile")
                    .commit()
            }
            textClear.setOnClickListener {
                MySharedPreferences.clearDataCache()
                viewModel.clearDatabase()
                Toast.makeText(context, "CLEAR SUCCESSFUL", Toast.LENGTH_SHORT).show()
            }
            textLogout.setOnClickListener {
                MySharedPreferences.clearPreferences()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ProfileSigninFragment())
                    .addToBackStack("profile")
                    .commit()
            }
            textChange.setOnClickListener {
                val fragmentChangePass = ChangePassFragment()
                val bundle = Bundle()
                bundle.putString("email", email)
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, fragmentChangePass.apply { arguments = bundle })
                    .addToBackStack("profile")
                    .commit()
            }
            linearMyOrder.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, OrderHistoryFragment())
                    .addToBackStack("profile")
                    .commit()
            }
        }
    }

    private fun bindData(profile: Customer) {
        val imgAvatar = MySharedPreferences.getString("imageAvatar", "")
        if (imgAvatar != "") {
            binding?.apply {
                Glide.with(root)
                    .load(imgAvatar)
                    .centerCrop()
                    .into(imageAvatar)
            }
        } else {
            binding?.apply {
                Glide.with(root)
                    .load(profile.avatar)
                    .centerCrop()
                    .into(imageAvatar)
            }
        }
        binding?.textName?.text = profile.name
        binding?.textMail?.text = profile.email
        binding?.layoutLoading?.root?.visibility = View.INVISIBLE
    }
}