package com.example.book_tads.model

import androidx.room.PrimaryKey

data class Book(
    var name: String,
    var author: String,
    var year: Int,
    var grade: Float
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}