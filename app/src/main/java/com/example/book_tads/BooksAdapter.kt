package com.example.book_tads

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.book_tads.model.Book
import kotlinx.android.synthetic.main.activity_book__layout.view.*

class BooksAdapter(c: Context, f: List<Book>) : BaseAdapter() {
    var context: Context = c
    var books: List<Book> = f
    override fun getItem(position: Int): Any {
        return books.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return books.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var v = LayoutInflater.from(context).inflate(R.layout.activity_book__layout, parent, false)
        var nomeBook = v.findViewById<TextView>(R.id.nomeBooks)
        var imageBook = v.findViewById<ImageView>(R.id.imgBooks)
        var bookAtual = books.get(position)
        nomeBook.text = bookAtual.name
        imageBook.setImageResource(R.drawable.books)
        return v
    }

}