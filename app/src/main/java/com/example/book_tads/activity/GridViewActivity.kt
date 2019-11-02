package com.example.book_tads.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.book_tads.R
import com.example.book_tads.adapter.BooksAdapter
import com.example.book_tads.connection.AppDatabase
import com.example.book_tads.model.Book
import kotlinx.android.synthetic.main.activity_grid_view.*

class GridViewActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "database-book"
        )
            .allowMainThreadQueries()
            .build()
    }

    var listaBooks:List<Book>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view)

        listaBooks = db.bookDao().listAll()

        gridview.adapter = BooksAdapter(this, listaBooks as List<Book>)
        gridview.setOnItemClickListener{adapterView, view, i, l ->
        var bookSelecionado = listaBooks?.get(i)
            Toast.makeText(this, "${bookSelecionado?.name} id = ${bookSelecionado?.id}", Toast.LENGTH_SHORT).show()
        }
    }
}
