package com.example.BookShop

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.BookShop.databinding.ActivityMainBinding
import com.example.BookShop.ui.auth.signin.SignInFragment
import com.example.BookShop.ui.onboarding.OnboardingFragment
import com.example.BookShop.utils.MySharedPreferences


class MainActivity : AppCompatActivity() {
    private lateinit var bnd: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bnd = ActivityMainBinding.inflate(layoutInflater)
        val view: View = bnd.root
        setContentView(view)
        MySharedPreferences.init(this)
        val support = supportFragmentManager.beginTransaction()
        if (isFirstLaunch()) {
            val fragmentOnboard = OnboardingFragment()
            support.replace(R.id.container, fragmentOnboard).commit()
            setFirstLaunch(false)
        } else {
            val fragmentSignin = SignInFragment()
            support.replace(R.id.container, fragmentSignin).commit()
        }
    }

    private fun isFirstLaunch(): Boolean {
        return MySharedPreferences.getBoolean("firstLaunch", true)
    }

    private fun setFirstLaunch(isFirstTime: Boolean) {
        MySharedPreferences.putBoolean("firstLaunch", isFirstTime)
    }
}