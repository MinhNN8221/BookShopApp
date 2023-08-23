package com.example.BookShop.ui.auth.signup

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.BookShop.R
import com.example.BookShop.data.api.RetrofitClient
import com.example.BookShop.data.model.AuthResponse
import com.example.BookShop.data.model.Customer
import com.example.BookShop.databinding.FragmentSignUpBinding
import com.example.BookShop.ui.auth.signin.SignInFragment
import com.example.BookShop.utils.AlertMessageViewer
import com.example.BookShop.utils.LoadingProgressBar
import com.example.BookShop.utils.MySharedPreferences

class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private lateinit var viewModel: SignUpViewModel
    private var binding: FragmentSignUpBinding? = null
    private var checkVisiblePass = false
    private var checkVisibleConfirm = false
    private lateinit var loadingProgressBar: LoadingProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignUpBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SignUpViewModel::class.java]
        initViewModel()
        loadingProgressBar = LoadingProgressBar(requireContext())
        binding?.apply {
            textLogin.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignInFragment())
                    .commit()
            }
            buttonRegister.setOnClickListener {
                val customer = Customer(
                    email = editEmail.text.toString(),
                    name = editName.text.toString(),
                    password = editPassword.text.toString(),
                    passwordAgain = editConfirmPassword.text.toString()
                )
                loadingProgressBar.show()
                val user = AuthResponse(customer = customer)
                viewModel.checkFields(user)
//                layoutLoading.root.setBackgroundColor(resources.getColor(R.color.secondary))
//                layoutLoading.root.visibility = View.VISIBLE
            }
            imageEyePassword.setOnClickListener {
                val cursorPosition = editPassword.selectionEnd
                if (!checkVisiblePass) {
                    editPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    checkVisiblePass = true
                    imageEyePassword.setImageResource(R.drawable.ic_hide_eye)
                } else {
                    editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                    checkVisiblePass = false
                    imageEyePassword.setImageResource(R.drawable.ic_visible_eye)
                }
                if (cursorPosition >= 0) {
                    editPassword.setSelection(cursorPosition)
                }
            }
            imageEyeConfirmPassword.setOnClickListener {
                val cursorPosition = editConfirmPassword.selectionEnd
                if (!checkVisibleConfirm) {
                    editConfirmPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    checkVisibleConfirm = true
                    imageEyeConfirmPassword.setImageResource(R.drawable.ic_hide_eye)
                } else {
                    editConfirmPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    imageEyeConfirmPassword.setImageResource(R.drawable.ic_visible_eye)
                    checkVisibleConfirm = false
                }
                if (cursorPosition >= 0) {
                    editConfirmPassword.setSelection(cursorPosition)
                }
            }
            textLogin.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignInFragment())
                    .commit()
            }
        }
    }

    private fun initViewModel() {
        viewModel.registerResponse.observe(viewLifecycleOwner) {
//            binding?.layoutLoading?.root?.visibility = View.INVISIBLE
            loadingProgressBar.cancel()
            it?.let { authState ->
//                authState.loginResponse?.let {
//
//                }
                AlertMessageViewer.showAlertDialogMessage(
                    requireContext(),
                    authState.error.message
                )
            }
        }
    }

}