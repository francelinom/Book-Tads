package com.example.book_tads.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book")
data class Book(
    var name: String,
    var author: String,
    var year: Int,
    var grade: Float
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}