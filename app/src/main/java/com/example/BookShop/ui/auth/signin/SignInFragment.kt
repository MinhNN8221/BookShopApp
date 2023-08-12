package com.example.BookShop.ui.auth.signin

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.BookShop.R
import com.example.BookShop.data.api.RetrofitClient
import com.example.BookShop.databinding.FragmentSignInBinding
import com.example.BookShop.ui.main.MainMenuFragment
import com.example.BookShop.utils.MySharedPreferences

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel
    private var binding: FragmentSignInBinding? = null
    private var checkVisible = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[SignInViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        val accessToken = MySharedPreferences.getAccessToken(null)
        if (accessToken != null) {
            navigateToMainScreen()
            RetrofitClient.updateAccessToken(accessToken)
        }
        binding?.apply {
            layoutSignIn.setOnTouchListener { view, motionEvent ->
                if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                    val event =
                        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    event.hideSoftInputFromWindow(requireView().windowToken, 0)
                }
                false
            }
            buttonLogin.setOnClickListener {
                val username = editUsername.text.toString()
                val password = editPassword.text.toString()
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(
                        requireContext(),
                        "Please enter username and password!!!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    viewModel.checkLogin(username, password)
                }
            }
            imageEye.setOnClickListener {
                val cursorPosition = editPassword.selectionEnd
                if (!checkVisible) {
                    editPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    checkVisible = true
                } else {
                    editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                    checkVisible = false
                }
                if (cursorPosition >= 0) {
                    editPassword.setSelection(cursorPosition)
                }
            }
        }
    }

    fun initViewModel() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            Log.d("HELLO", it.toString())
            if (it.checkLogin) {
                navigateToMainScreen()
                it.loginResponse?.let { loginResponse ->
                    MySharedPreferences.putAccessToken(loginResponse.accessToken)
                    RetrofitClient.updateAccessToken(loginResponse.accessToken)
                }
                it.loginResponse?.customer?.customer_id?.let { idCustomer ->
                    MySharedPreferences.putInt(
                        "idCustomer",
                        idCustomer
                    )
                }
                Toast.makeText(requireContext(), "Login Successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Login Fail!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun navigateToMainScreen() {
        val fragment = MainMenuFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack("SignInFragment")
        transaction.commit()
    }
}