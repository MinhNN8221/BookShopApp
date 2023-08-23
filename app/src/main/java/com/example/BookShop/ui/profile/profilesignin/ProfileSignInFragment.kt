package com.example.BookShop.ui.profile.profilesignin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.BookShop.R
import com.example.BookShop.databinding.FragmentProfileSigninBinding
import com.example.BookShop.ui.auth.signin.SignInFragment
import com.example.BookShop.ui.auth.signup.SignUpFragment

class ProfileSignInFragment : Fragment() {

    private var binding: FragmentProfileSigninBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileSigninBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            textBtnSignin.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignInFragment())
                    .commit()
            }
            textCreate.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignUpFragment())
                    .commit()
            }
        }
    }
}