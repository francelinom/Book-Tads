package com.example.book_tads.adapter

import android.content.Context
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.book_tads.R
import com.example.book_tads.holder.BookViewHolderSwipeDragDrop
import com.example.book_tads.model.Book
import java.util.Collections.swap

class BookAdapterSwipeDragDrop(var c: Context, var frutas: MutableList<Book>) :
    RecyclerView.Adapter<BookViewHolderSwipeDragDrop>() {

    private val PENDING_REMOVAL_TIMEOUT: Long = 3000 // 3sec
    var itemsPendingRemoval = ArrayList<Book>()

    private val handler = Handler() // hanlder que vai guardar os runnables que devem ser executados
    var pendingRunnables: HashMap<Book, Runnable> =
        HashMap() // map de frutas com runnables pendentes, para que seja possível cancelar

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolderSwipeDragDrop {
        val view = LayoutInflater.from(c).inflate(R.layout.inflater_swipe_drag_drop, parent, false);

        return BookViewHolderSwipeDragDrop(view)
    }

    override fun getItemCount(): Int {
        return frutas.size
    }

    override fun onBindViewHolder(holder: BookViewHolderSwipeDragDrop, position: Int) {

        val frutaescolhida = frutas[position]
        holder.textViewNome.text = frutaescolhida.name
        holder.img.setImageResource(R.drawable.books)



        if (itemsPendingRemoval.contains(frutaescolhida)) {
            //view do swipe/delete
            holder.layoutNormal.setVisibility(View.GONE)
            holder.layoutGone.setVisibility(View.VISIBLE)
            holder.undoButton.setVisibility(View.VISIBLE)
            holder.undoButton.setOnClickListener {
                // usou o undo, remover a pendingRennable
                val pendingRemovalRunnable = pendingRunnables[frutaescolhida]
                Log.i("AULA17", "CLICOU")
                pendingRunnables.remove(frutaescolhida)
                if (pendingRemovalRunnable != null) {
                    handler.removeCallbacks(pendingRemovalRunnable)
                }
                itemsPendingRemoval.remove(frutaescolhida)
                //binda novamente para redesenhar
                notifyItemChanged(frutas.indexOf(frutaescolhida))
            }
        } else {
            //mostra o padrão
            holder.textViewNome.setText(frutaescolhida.name)
            holder.layoutNormal.setVisibility(View.VISIBLE)
            holder.layoutGone.setVisibility(View.GONE)
            holder.undoButton.setVisibility(View.GONE)
            holder.undoButton.setOnClickListener(null)

        }


    }

    fun remover(position: Int) {
        var fruta = frutas[position]

        if (frutas.contains(fruta)) {
            frutas.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    fun removerComTempo(position: Int) {

        val fruta = frutas[position]
        if (!itemsPendingRemoval.contains(fruta)) {
            itemsPendingRemoval.add(fruta)
            notifyItemChanged(position)
            var pendingRemovalRunnable = Runnable {
                remover(position)
            }
            handler.postDelayed(pendingRemovalRunnable, PENDING_REMOVAL_TIMEOUT)
            pendingRunnables[fruta] = pendingRemovalRunnable
        }
    }

    fun mover(fromPosition: Int, toPosition: Int) {

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                swap(frutas, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                swap(frutas, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
        notifyItemChanged(toPosition)
        notifyItemChanged(fromPosition)
    }
}