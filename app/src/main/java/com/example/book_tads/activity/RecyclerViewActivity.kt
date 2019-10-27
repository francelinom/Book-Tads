package com.example.book_tads.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.book_tads.R
import com.example.book_tads.adapter.BookAdapterRecyclerView
import com.example.book_tads.adapter.MyRecyclerViewClickListener
import com.example.book_tads.connection.AppDatabase
import com.example.book_tads.model.Book
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_recycler_view.*

class RecyclerViewActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_recycler_view)

        var listaFrutas: MutableList<Book> = db.bookDao().listAll()

        var adapter = BookAdapterRecyclerView(this, listaFrutas)
        recyclerview.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerview.layoutManager = layout

        recyclerview.addOnItemTouchListener(
            MyRecyclerViewClickListener(
                this@RecyclerViewActivity,
                recyclerview,
                object : MyRecyclerViewClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Toast.makeText(this@RecyclerViewActivity, "Clique simples", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onItemLongClick(view: View, position: Int) {
                        val removida = listaFrutas[position]
                        listaFrutas.remove(removida)
                        recyclerview.adapter!!.notifyItemRemoved(position)
                        Toast.makeText(this@RecyclerViewActivity, "Clique longo", Toast.LENGTH_SHORT)
                            .show()
                        val snack = Snackbar.make(
                            recyclerview.parent as View, "Removido", Snackbar.LENGTH_LONG
                        )
                            .setAction("Cancelar") {
                                listaFrutas.add(position, removida)
                                recyclerview.adapter!!.notifyItemInserted(position)
                            }
                        snack.show()
                    }
                })
        )

        recyclerview.itemAnimator = DefaultItemAnimator()
        //recyclerview.itemAnimator = LandingAnimator()
        //recyclerview.itemAnimator = FlipInTopXAnimator()
    }
}
