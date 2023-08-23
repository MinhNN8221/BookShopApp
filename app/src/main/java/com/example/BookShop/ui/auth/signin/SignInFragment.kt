package com.example.BookShop.ui.auth.signin

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.BookShop.R
import com.example.BookShop.data.api.RetrofitClient
import com.example.BookShop.data.model.Customer
import com.example.BookShop.data.model.AuthResponse
import com.example.BookShop.databinding.FragmentSignInBinding
import com.example.BookShop.ui.auth.forgot.ForgotPasswordFragment
import com.example.BookShop.ui.auth.signup.SignUpFragment
import com.example.BookShop.ui.main.MainMenuFragment
import com.example.BookShop.utils.AlertMessageViewer
import com.example.BookShop.utils.LoadingProgressBar
import com.example.BookShop.utils.MySharedPreferences

class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private lateinit var viewModel: SignInViewModel
    private var binding: FragmentSignInBinding? = null
    private var checkVisible = false
    private lateinit var loadingProgressBar: LoadingProgressBar
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
            navToMainScreen()
            RetrofitClient.updateAccessToken(accessToken)
        }
        loadingProgressBar = LoadingProgressBar(requireContext())
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
                val customer = Customer(email = username, password = password)
                val user = AuthResponse(customer = customer)
                viewModel.checkFields(user)
                loadingProgressBar.show()
            }
            textForgotpass.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, ForgotPasswordFragment())
                    .addToBackStack("SignIn")
                    .commit()
            }
            textRegister.setOnClickListener {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.container, SignUpFragment())
                    .addToBackStack("SignIn")
                    .commit()
            }
            imageEye.setOnClickListener {
                val cursorPosition = editPassword.selectionEnd
                if (!checkVisible) {
                    editPassword.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    checkVisible = true
                    imageEye.setImageResource(R.drawable.ic_hide_eye)
                } else {
                    editPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                    checkVisible = false
                    imageEye.setImageResource(R.drawable.ic_visible_eye)
                }
                if (cursorPosition >= 0) {
                    editPassword.setSelection(cursorPosition)
                }
            }
        }
    }

    fun initViewModel() {
        viewModel.loginResponse.observe(viewLifecycleOwner) {
            loadingProgressBar.cancel()
            if (it?.loginResponse == null) {
                it.error.message.let { it1 ->
                    AlertMessageViewer.showAlertDialogMessage(
                        requireContext(),
                        it1
                    )
                }
            } else {
                navToMainScreen()
                MySharedPreferences.putAccessToken(it.loginResponse.accessToken)
                RetrofitClient.updateAccessToken(it.loginResponse.accessToken)
                it.loginResponse.customer.customerId?.let { idCustomer ->
                    MySharedPreferences.putInt(
                        "idCustomer",
                        idCustomer
                    )
                }
            }
        }
    }

    fun navToMainScreen() {
        val fragment = MainMenuFragment()
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack("SignInFragment")
        transaction.commit()
    }
}