package com.example.book_tads.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.book_tads.R
import com.example.book_tads.model.Book

class PageViewAdapter(var context: Context, var books: List<Book>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.inflater_page_view, container, false)
        val img: ImageView = view.findViewById(R.id.imagemPersonagem)
        img.setImageResource(R.drawable.books)
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }

    override fun isViewFromObject(view: View, obj: Any): Boolean {
        return view == obj
    }

    override fun getCount(): Int {
        return books.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return books[position].name
    }
}