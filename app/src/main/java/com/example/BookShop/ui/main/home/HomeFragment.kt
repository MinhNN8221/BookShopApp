package com.example.BookShop.ui.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.BookShop.R
import com.example.BookShop.data.model.Book
import com.example.BookShop.data.model.Category
import com.example.BookShop.data.model.DiscoverStore
import com.example.BookShop.data.model.NewArrival
import com.example.BookShop.ui.main.adapter.BookAdapter
import com.example.BookShop.ui.main.adapter.CategoryAdapter
import com.example.BookShop.ui.main.adapter.DiscoverStoreAdapter
import com.example.BookShop.ui.main.adapter.NewArrivalAdapter

class HomeFragment : Fragment() {
    private val discoverStoreList = mutableListOf<DiscoverStore>()
    private val categoryList = mutableListOf<Category>()
    private val newArrivalList = mutableListOf<NewArrival>()
    private val bookList = mutableListOf<Book>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview_discoverstore)
        val recyclerView1: RecyclerView = view.findViewById(R.id.recyclerview_category)
        val recyclerView2: RecyclerView = view.findViewById(R.id.recyclerview_newarrival)
        val recyclerView3: RecyclerView = view.findViewById(R.id.recyclerview_hotbook)

        val layoutManagerHorizontal = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView.layoutManager = layoutManagerHorizontal

        val layoutManagerHorizontal1 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView1.layoutManager = layoutManagerHorizontal1

        val layoutManagerHorizontal2 = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.layoutManager = layoutManagerHorizontal2

        recyclerView3.layoutManager = GridLayoutManager(requireContext(), 2)


        discoverStoreList.add(DiscoverStore("Store 1"))
        discoverStoreList.add(DiscoverStore("Store 2"))
        discoverStoreList.add(DiscoverStore("Store 2"))
        discoverStoreList.add(DiscoverStore("Store 2"))
        discoverStoreList.add(DiscoverStore("Store 2"))
        discoverStoreList.add(DiscoverStore("Store 2"))


        categoryList.add(Category("Category 1"))
        categoryList.add(Category("Category 2"))
        categoryList.add(Category("Category 2"))
        categoryList.add(Category("Category 2"))
        categoryList.add(Category("Category 2"))


        newArrivalList.add(NewArrival("Book 1", "10.99", "8.99"))
        newArrivalList.add(NewArrival("Book 2", "15.99", "12.49"))
        newArrivalList.add(NewArrival("Book 2", "15.99", "12.49"))
        newArrivalList.add(NewArrival("Book 2", "15.99", "12.49"))
        newArrivalList.add(NewArrival("Book 2", "15.99", "12.49"))
        newArrivalList.add(NewArrival("Book 2", "15.99", "12.49"))


        bookList.add(Book("Book1", "120", "120"))
        bookList.add(Book("Book1", "120", "120"))
        bookList.add(Book("Book1", "120", "120"))
        bookList.add(Book("Book1", "120", "120"))
        bookList.add(Book("Book1", "120", "120"))
        bookList.add(Book("Book1", "120", "120"))
        bookList.add(Book("Book1", "120", "120"))


        val adapter = DiscoverStoreAdapter(discoverStoreList)
        recyclerView.adapter = adapter

        val adapter1 = CategoryAdapter(categoryList)
        recyclerView1.adapter = adapter1

        val adapter2 = NewArrivalAdapter(newArrivalList)
        recyclerView2.adapter = adapter2

        val adapter3 = BookAdapter(bookList)
        recyclerView3.adapter = adapter3

        return view
    }
}