package com.example.BookShop.ui.profile.changepass

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.BookShop.databinding.FragmentChangePassBinding

class ChangePassFragment : Fragment() {

    companion object {
        fun newInstance() = ChangePassFragment()
    }

    private lateinit var viewModel: ChangePassViewModel
    private var binding: FragmentChangePassBinding? = null
    private var checkVisible = false
    private var checkVisibleNewPass = false
    private var checkVisibleConfirmPass = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentChangePassBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChangePassViewModel::class.java)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.imageLeft?.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding?.apply {
            textUpdatePassword.setOnClickListener {
                val email = arguments?.getString("email").toString()
                val oldPass = editCurrentPass.text.toString()
                val newPass = editNewPass.text.toString()
                val confirmPass = editConfirm.text.toString()
                if (newPass == confirmPass) {
                    if(oldPass.length<5 || newPass.length<5){
                        Toast.makeText(context, "Mật khẩu lớn hơn =5 kí tự", Toast.LENGTH_SHORT).show()
                    }else{
                        viewModel.changePassword(email, oldPass, newPass)
                        viewModel.message.observe(viewLifecycleOwner, Observer {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        })
                    }
                }
                else {
                    Toast.makeText(context, "Confirm failure", Toast.LENGTH_SHORT).show()
                }
            }
            imageEyeCurrentPass.setOnClickListener {
                val cursorPosition = editCurrentPass.selectionEnd
                if (!checkVisible) {
                    editCurrentPass.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    checkVisible = true
                } else {
                    editCurrentPass.transformationMethod = PasswordTransformationMethod.getInstance()
                    checkVisible = false
                }
                if (cursorPosition >= 0) {
                    editCurrentPass.setSelection(cursorPosition)
                }
            }
            imageEyeNewPass.setOnClickListener {
                val cursorPosition = editNewPass.selectionEnd
                if (!checkVisibleNewPass) {
                    editNewPass.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    checkVisibleNewPass = true
                } else {
                    editCurrentPass.transformationMethod = PasswordTransformationMethod.getInstance()
                    checkVisibleNewPass = false
                }
                if (cursorPosition >= 0) {
                    editNewPass.setSelection(cursorPosition)
                }
            }
            imageEyeConfirmPass.setOnClickListener {
                val cursorPosition = editConfirm.selectionEnd
                if (!checkVisibleConfirmPass) {
                    editConfirm.transformationMethod =
                        HideReturnsTransformationMethod.getInstance()
                    checkVisibleConfirmPass = true
                } else {
                    editConfirm.transformationMethod = PasswordTransformationMethod.getInstance()
                    checkVisibleConfirmPass = false
                }
                if (cursorPosition >= 0) {
                    editConfirm.setSelection(cursorPosition)
                }
            }
        }
    }
}