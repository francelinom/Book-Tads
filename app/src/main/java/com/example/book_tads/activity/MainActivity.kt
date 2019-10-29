package com.example.book_tads.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.book_tads.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCadastrar.setOnClickListener {
            startActivity(Intent(this, RegisterBookActivity::class.java))
        }

        buttonListarLivros.setOnClickListener {
            startActivity(Intent(this, ListBookActivity::class.java))
        }

        buttonAutoComplete.setOnClickListener {
            startActivity(Intent(this, AutoCompleteActivity::class.java))
        }

        buttonListView.setOnClickListener {
            startActivity(Intent(this, Book_LayoutActivity::class.java))
        }

        buttonPageView.setOnClickListener {
            startActivity(Intent(this, PageViewActivity::class.java))
        }

        buttonSwipeDragDrop.setOnClickListener {
            startActivity(Intent(this, SwipeDragDropActivity::class.java))
        }

        buttonRecyclerView.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }


    }
}
