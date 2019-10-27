package com.example.book_tads.dao

import androidx.room.*
import com.example.book_tads.model.Book


@Dao
interface BookDao {

    @Insert
    fun insert(book: Book): Long

    @Delete
    fun delete(book: Book): Int

    @Update
    fun update(book: Book): Int

    @Query("SELECT * FROM BOOK")
    fun listAll(): MutableList<Book>

    @Query("SELECT * FROM BOOK WHERE id = :id")
    fun findById(id: Long): Book

    @Query("SELECT * FROM BOOK WHERE name = :nome")
    fun findByName(nome: String): Book

}