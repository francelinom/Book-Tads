package com.example.book_tads.activity

import android.graphics.Canvas
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.book_tads.R
import com.example.book_tads.adapter.BookAdapterSwipeDragDrop
import com.example.book_tads.connection.AppDatabase
import com.example.book_tads.model.Book
import kotlinx.android.synthetic.main.activity_swipe_drag_drop.*

class SwipeDragDropActivity : AppCompatActivity() {

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
        setContentView(R.layout.activity_swipe_drag_drop)



        var listBooks: MutableList<Book> = db.bookDao().listAll()

        var adapter = BookAdapterSwipeDragDrop(this, listBooks)
        recyclerview.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerview.layoutManager = layout



        //EXEMPLO 1

        /*

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                Log.i(
                    "AULA17",
                    "Drag flags: " + Integer.toBinaryString(dragFlags) + "Swipe flags: " + Integer.toBinaryString(
                        swipeFlags
                    )
                ) //11 e 110000
                return makeMovementFlags(dragFlags, swipeFlags)
            }
            override fun onMove(recyclerView: RecyclerView, dragged: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                Log.i(
                    "AULA17",
                    "OnMove invocado. Mover da posição " + dragged.adapterPosition + " para " + target.adapterPosition
                )
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                Log.i("AULA17", "OnSwipe invocado. Direção: " + Integer.toBinaryString(direction))
            }
        })

         */


        /*
    EXEMPLO 2
    val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
        UP or DOWN, START or END  )
    {
        override fun onMove( recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,target: RecyclerView.ViewHolder): Boolean {
            Log.i("AULA17", "OnMove")
            return false
        }
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            Log.i("AULA17", "OnSwiped")
        }
    })
     */


        //EXEMPLO 3


        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            UP or DOWN, START or END
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                Log.i("AULA17", "OnMove")
                //é usado para operações drag and drop
                val fromPosition = viewHolder.adapterPosition
                val toPosition = target.adapterPosition
                val adapter = recyclerView.adapter as BookAdapterSwipeDragDrop
                adapter.mover(fromPosition, toPosition)
                return true// true se moveu, se não moveu, retorne falso
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                var posicao = viewHolder.adapterPosition
                var adapter = recyclerview.adapter as BookAdapterSwipeDragDrop

                //adapter.remover(posicao)
                adapter.removerComTempo(posicao)

            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val background = ColorDrawable(resources.getColor(R.color.red))
                // not sure why, but this method get's called for viewholder that are already swiped away
                if (viewHolder.adapterPosition === -1) {
                    // not interested in those
                    return
                }
                Log.i("AULA17", "dx = $dX")
                // Here, if dX > 0 then swiping right.
                // If dX < 0 then swiping left.
                // If dX == 0 then at at start position.
                // draw red background
                if (dX < 0) {
                    Log.i("AULA17", "dX < 0")
                    background.setBounds(
                        (itemView.right + dX).toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                } else if (dX > 0) {
                    Log.i("AULA17", "dX > 0")
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        (dX).toInt(),
                        itemView.bottom
                    )
                }
                background.draw(c)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )

            }

            override fun isLongPressDragEnabled(): Boolean {
                //return false; se quiser, é possivel desabilitar o drag and drop
                return true
            }

            override fun isItemViewSwipeEnabled(): Boolean {
                //return false; se quiser, é possivel desabilitar o swipe
                return true
            }

        })



        itemTouchHelper.attachToRecyclerView(recyclerview)


    }

}
