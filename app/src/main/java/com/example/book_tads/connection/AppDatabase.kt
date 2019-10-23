package com.example.book_tads.connection

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.book_tads.dao.BookDao
import com.example.book_tads.model.Book

@Database(entities = [Book::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}