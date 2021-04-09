package com.example.cocktailapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CocktailData(
    @PrimaryKey(autoGenerate = true) var uid: Int? = null,
    var id: Int?,
    var name: String?,
    val instruction: String?,
    val glass: String?,
    val tags: String?,
    val img: String?
)