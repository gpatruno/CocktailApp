package com.example.cocktailapp

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cocktailapp.data.CocktailDao
import com.example.cocktailapp.data.CocktailData

@Database(entities = arrayOf(CocktailData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao
}
