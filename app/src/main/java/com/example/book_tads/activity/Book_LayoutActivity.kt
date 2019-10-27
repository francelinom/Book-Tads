package com.example.book_tads.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.book_tads.R
import com.example.book_tads.adapter.BooksAdapter
import com.example.book_tads.connection.AppDatabase
import com.example.book_tads.model.Book
import kotlinx.android.synthetic.main.activity_list_view.*

class Book_LayoutActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "database-book"
        )
            .allowMainThreadQueries()
            .build()
    }

    var listBooks:List<Book>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)


        listBooks = db.bookDao().listAll()

        listviewLayout.adapter =
            BooksAdapter(this, listBooks as List<Book>)
        listviewLayout.setOnItemClickListener { adapterView, view, i, l ->
            var bookSelected = listBooks?.get(i)
            Toast.makeText(this, "${bookSelected?.name} id=${bookSelected?.id}", Toast.LENGTH_SHORT)
                .show()
        }
    }
}
