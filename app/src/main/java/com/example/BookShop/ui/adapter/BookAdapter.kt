package com.example.BookShop.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.BookShop.data.model.Product
import com.example.BookShop.databinding.ItemProductBinding
import com.example.BookShop.ui.adapter.OnItemClickListener
import com.example.BookShop.utils.FormatMoney

class BookAdapter(private var productList: List<Product>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null
    private val formatMoney= FormatMoney()
    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
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
                .load(product.image)
                .centerCrop()
                .into(binding.imageProduct)
            binding.textPrice.text = product.price?.toDouble()
                ?.let { formatMoney.formatMoney(it.toLong()) }
            binding.textName.text = product.name
            binding.cardview.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
        }
    }
}