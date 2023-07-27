package com.example.BookShop.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.BookShop.R
import com.example.BookShop.databinding.FragmentProfileBinding
import com.example.BookShop.ui.profile.changepass.ChangePassFragment
import com.example.BookShop.ui.profile.profilesignin.ProfileSigninFragment
import com.example.BookShop.ui.profile.updateprofile.UpdateProfileFragment

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
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.imageLeft?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding?.imageUpdate?.setOnClickListener {
            val fragmentUpdate = UpdateProfileFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragmentUpdate)
                .addToBackStack("profile")
                .commit()
        }
        binding?.textChange?.setOnClickListener {
            val fragmentChangePass = ChangePassFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragmentChangePass)
                .addToBackStack("profile")
                .commit()
        }
        binding?.textLogout?.setOnClickListener {
            val fragmentSignin = ProfileSigninFragment()
            parentFragmentManager.beginTransaction()
                .replace(R.id.frame_layout, fragmentSignin)
                .addToBackStack("profile")
                .commit()
        }
    }
}