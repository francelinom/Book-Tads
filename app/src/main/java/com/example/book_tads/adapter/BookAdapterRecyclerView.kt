package com.example.book_tads.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.book_tads.R
import com.example.book_tads.holder.BookViewHolder
import com.example.book_tads.model.Book

class BookAdapterRecyclerView(var c: Context, var books: List<Book>) :
    RecyclerView.Adapter<BookViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view =
            LayoutInflater.from(c).inflate(R.layout.inflater_book_recycler_view, parent, false)

        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val booksEcolhida = books[position]
        holder.textViewTitulo.text = booksEcolhida.name
        holder.img.setImageResource(R.drawable.books)
    }
}