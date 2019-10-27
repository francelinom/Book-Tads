package com.example.book_tads.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.book_tads.R
import com.example.book_tads.connection.AppDatabase
import com.example.book_tads.model.Book
import kotlinx.android.synthetic.main.activity_register_book.*

class RegisterBookActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_register_book)

        buttonSalvar.setOnClickListener {
            saveBook()
        }

        buttonCancelar.setOnClickListener {
            Toast.makeText(this, "CANCELADO", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    fun saveBook() {
        try {
            db.bookDao().insert(
                Book(
                    textTitle.text.toString(),
                    textAuthor.text.toString(),
                    Integer.parseInt(textYear.text.toString()),
                    ratingBar.rating
                )
            )
        } catch (e: java.lang.NumberFormatException) {
            db.bookDao().insert(
                Book(
                    textTitle.text.toString(),
                    textAuthor.text.toString(),
                    0,
                    ratingBar.rating
                )
            )
        }
        Toast.makeText(this, "CADASTRADO", Toast.LENGTH_SHORT).show()

        textTitle.setText("")
        textAuthor.setText("")
        textYear.setText("")
        ratingBar.rating = 0f
    }
}
