package com.example.BookShop.ui.adapter

import android.annotation.SuppressLint
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.BookShop.data.model.Product
import com.example.BookShop.databinding.ItemProductBinding
import com.example.BookShop.utils.FormatMoney

class ProductAdapter() :
    RecyclerView.Adapter<ProductAdapter.ViewHolder>() {
    private var productList: MutableList<Product> = mutableListOf()
    private var onItemClickListener: OnItemClickListener? = null
    private var addItemToCart: OnItemClickListener? = null
    private val formatMoney = FormatMoney()

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        productList.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(products: List<Product>) {
        productList.clear()
        productList.addAll(products)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun setAddItemToCart(listener: OnItemClickListener) {
        addItemToCart = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    fun getBook(position: Int): Product = productList[position]

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
            Glide.with(binding.root)
                .load(product.thumbnail)
                .centerCrop()
                .into(binding.imageProduct)
            if (product.discounted_price != null) {
                val layoutParams =
                    binding.textPrice.layoutParams as ViewGroup.MarginLayoutParams
                val newMarginTopInDp = 0
                binding.textDiscountPrice.visibility = View.VISIBLE
                layoutParams.topMargin = newMarginTopInDp
                binding.textPrice.layoutParams = layoutParams
                binding.textDiscountPrice.text =
                    product.discounted_price?.toDouble()
                        ?.let { formatMoney.formatMoney(it.toLong()) }
                binding.textPrice.text = setPrice(
                    product.price?.toDouble()
                        ?.let { formatMoney.formatMoney(it.toLong()) }.toString()
                )
            } else {
                binding.textPrice.text = product.price?.toDouble()
                    ?.let { formatMoney.formatMoney(it.toLong()) }
            }

            binding.textName.text = product.name
            binding.cardview.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
            binding.imageCart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
            binding.imageCart.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    addItemToCart?.onItemClick(position)
                }
            }
        }
    }

    private fun setPrice(price: String): SpannableString {
        val content = SpannableString(price)
        content.setSpan(
            StrikethroughSpan(),
            0,
            price.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        content.setSpan(
            RelativeSizeSpan(12 / 14f),
            0,
            price.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return content
    }
}