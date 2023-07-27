package com.example.BookShop.ui.productdetail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.BookShop.R
import com.example.BookShop.data.model.ProductInfoList
import com.example.BookShop.databinding.FragmentProductDetailBinding
import com.example.BookShop.ui.author.AuthorFragment
import com.example.BookShop.ui.productdetail.ProductdetailViewModel
import com.example.BookShop.ui.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductdetailFragment : Fragment() {
    private var binding: FragmentProductDetailBinding? = null
    private lateinit var viewModel: ProductdetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProductDetailBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProductdetailViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bottomNavigationView =
            requireActivity().findViewById<BottomNavigationView>(R.id.navigation)
        bottomNavigationView.visibility = View.GONE

        val product_id = arguments?.getString("book_id")?.toInt()
        product_id?.let {
            viewModel.getProductInfo(it)
            viewModel.productInfo.observe(viewLifecycleOwner, Observer { productInfoList ->
                if (productInfoList != null) {
                    bindData(productInfoList)
                } else {
                    Log.d("NULLLL", "HEllo")
                }
            })
        }


        readmoreInfo()

        binding?.apply {
            imageLeft.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            imageAccount.setOnClickListener {
                val profileFragment = ProfileFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, profileFragment)
                    .addToBackStack("productFragment")
                    .commit()
            }
            textNameAuthor.setOnClickListener {
                val authorFragment = AuthorFragment()
                parentFragmentManager.beginTransaction()
                    .replace(R.id.frame_layout, authorFragment)
                    .addToBackStack("productFragment")
                    .commit()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun bindData(productInfoList: ProductInfoList) {

        binding?.apply {
            Glide.with(root)
                .load(productInfoList.product.thumbnail)
                .centerCrop()
                .into(imagePro)
            textName.text = productInfoList.product.name
            textDescription.text = productInfoList.product.description
            textPrice.text = productInfoList.product.price
            textNameAuthor.text= setAuthorName(productInfoList.author.author_name)
            textNcc.text=resources.getString(R.string.supplier)+" "+productInfoList.supplier.supplier_name
            textPublish.text=productInfoList.supplier.supplier_name
            if(productInfoList.product.wishlist==1){
                imageFavorite.setBackgroundResource(R.drawable.bg_ellipse_favor)
                imageFavorite.setImageResource(R.drawable.ic_favorite)
            }
        }
    }

    private fun readmoreInfo() {
        var check = true
        binding?.apply {
            val layoutParams = constraintImageProduct.layoutParams as ConstraintLayout.LayoutParams
            readmore.setOnClickListener {
                if (check) {
                    layoutParams.dimensionRatio = "12:7"
                    constraintImageProduct.layoutParams = layoutParams
                    val newMaxLines = Integer.MAX_VALUE
                    textDescription.maxLines = newMaxLines
                    check = false
                    readmore.text = "Collapse."
                } else {
                    layoutParams.dimensionRatio = "6:4"
                    constraintImageProduct.layoutParams = layoutParams
                    val newMaxLines = 2
                    textDescription.maxLines = newMaxLines
                    readmore.text = "Read more."
                    check = true
                }
            }
        }
    }

    private fun setAuthorName(name: String): SpannableString {
        val content = SpannableString(name)
        content.setSpan(UnderlineSpan(), 0, name.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        val underlineColor = resources.getColor(R.color.colorAuth)
        content.setSpan(
            ForegroundColorSpan(underlineColor),
            0,
            name.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return content
    }
}