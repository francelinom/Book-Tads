package com.example.book_tads.holder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.book_tads.R

class BookViewHolderSwipeDragDrop (v: View) : RecyclerView.ViewHolder(v){
    val textViewNome: TextView = v.findViewById(R.id.nomeBook)
    val img: ImageView = v.findViewById(R.id.img)
    val layoutNormal: LinearLayout = v.findViewById(R.id.layout_normal)
    val layoutGone:LinearLayout = v.findViewById(R.id.layout_gone)

    val undoButton: Button = v.findViewById(R.id.undo_button)
}