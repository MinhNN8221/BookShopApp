package com.example.BookShop.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.BookShop.data.model.Product
import com.example.BookShop.databinding.ItemProductBinding

class BookAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    private var onItemClickListener: OnItemClickListener? = null
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookAdapter.ViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
        holder.itemView.setOnClickListener(View.OnClickListener {
        })
    }

    override fun getItemCount(): Int = productList.size

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(product: Product) {
//            Glide.with(binding.root)
//                .load(product.image) // Đường dẫn hình ảnh của sản phẩm (đường dẫn HTTPS)
//                .centerCrop()
////                .placeholder(R.drawable.placeholder_image)
////                .error(R.drawable.error_image)
//                .into(binding.imageProduct)
            binding.textPrice.text = product.price
            binding.textDescription.text = product.description
            binding.cardview.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onItemClick(position)
                }
            }
        }
    }


}