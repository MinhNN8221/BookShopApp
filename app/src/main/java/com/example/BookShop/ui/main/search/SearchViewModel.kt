package com.example.BookShop.ui.main.search

import androidx.lifecycle.ViewModel
import com.example.BookShop.data.model.Product

class SearchViewModel : ViewModel(){
    private val bookList= mutableListOf<Product>()
    private val historyList= mutableListOf<Product>()
    init {
        bookList.add(
            Product(1, "Đắc nhân tâm", "Descriptisfdson", "1000VND",
                "200VND", "https://cdn0.fahasa.com/media/catalog/product/h/o/hoi-chung-tuoi-thanh-xuan_9_ban-pho-thong.jpg", "", "", 0, 0, 0))
        bookList.add(Product(3, "Đắc đạo", "Descriptisfdson", "1000VND",
            "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
        bookList.add(Product(2, "Đắc văn kỉ tử", "Descriptisfdson", "1000VND",
            "200VND", "https://cdn0.fahasa.com/media/catalog/product/8/9/8935280913738-dd.jpg", "", "", 0, 0, 0))
    }
    fun getProducts():List<Product>{
        return bookList
    }

    fun getHistory():List<Product>{
        return historyList
    }
}