package com.example.book_tads

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.room.Room
import com.example.book_tads.connection.AppDatabase
import com.example.book_tads.model.Book
import kotlinx.android.synthetic.main.activity_list_book.*

class ListBookActivity : AppCompatActivity() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "database-book"
        )
            .allowMainThreadQueries()
            .build()
    }


    var cont = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book)



        buttonProximo.setOnClickListener {
            cont++
            mudarLivro()
        }

        buttonVoltar.setOnClickListener {
            cont--
            mudarLivro()
        }


    }

    fun mudarLivro(){

        var books = db.bookDao().listAll()
        if (books.size > 0){
            textTitulo.text = books.get(cont).name
            textAutor.text = books.get(cont).author
            textAno.text = books.get(cont).year.toString()
            textNota.text = books.get(cont).grade.toString()

            buttonVoltar.visibility = View.VISIBLE
            buttonProximo.visibility = View.VISIBLE

            outroLivro()

        }else{
            buttonProximo.visibility = View.INVISIBLE
            buttonVoltar.visibility = View.INVISIBLE

            limpar()
        }
    }

    fun limpar(){
        textTitulo.text = ""
        textAutor.text = ""
        textAno.text = ""
        textNota.text = ""
    }

    fun outroLivro(){
        var books = db.bookDao().listAll()
        textTitulo.text = books.get(cont).name.toString()
        textAutor.text = books.get(cont).author.toString()
        textAno.text = books.get(cont).year.toString()
        textNota.text = books.get(cont).grade.toString()

        voltarOrProximo()
    }

    fun voltarOrProximo(){
        var books = db.bookDao().listAll()
        if (cont + 1 >= books.size){
            buttonProximo.visibility = View.INVISIBLE
        }else{
            buttonProximo.visibility = View.VISIBLE
        }

        if (cont - 1 < 0){
            buttonVoltar.visibility = View.INVISIBLE
        }else{
            buttonVoltar.visibility = View.VISIBLE
        }
    }

}
