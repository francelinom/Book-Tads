package com.example.book_tads.holder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.book_tads.R

class BookViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    val textViewTitulo: TextView
    val img: ImageView

    init {
        textViewTitulo = v.findViewById(R.id.title)
        img = v.findViewById(R.id.img)
    }

}


