package com.example.book_tads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.room.Room
import com.example.book_tads.connection.AppDatabase
import com.example.book_tads.model.Book
import kotlinx.android.synthetic.main.activity_auto_complete.*
import kotlinx.android.synthetic.main.activity_register_book.view.*

class AutoCompleteActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "database-book"
        )
            .allowMainThreadQueries()
            .build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_complete)


        var books = db.bookDao().listAll()

        var titles = Array<String>(books.size, { i -> i.toString() })
        for (i in 0 until books.size) {
            titles[i] = books[i].name
        }

        var bookToListAdapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_expandable_list_item_1,
            titles
        )

        autoCompleteTextView.setAdapter(bookToListAdapter)

        autoCompleteTextView.setOnItemClickListener { adapterView, view, i, l ->
            var selected = adapterView.getItemAtPosition(i)
            Toast.makeText(
                this,
                "$selected"
                , Toast.LENGTH_SHORT
            ).show()
            var books = db.bookDao().findByName(selected.toString())
            textTitulo.text = books.name
            textAutor.text = books.author
            textAno.text = books.year.toString()
            textNota.text = books.grade.toString()
        }
    }
}