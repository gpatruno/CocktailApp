package com.gpatruno.cocktailapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gpatruno.cocktailapp.data.CocktailDao
import com.gpatruno.cocktailapp.data.CocktailData

@Database(entities = arrayOf(CocktailData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cocktailDao(): CocktailDao

    companion object {
        private var RECIPE_INSTANCE: AppDatabase? = null
        internal fun getRecipeDatabase(context: Context): AppDatabase {
            if (RECIPE_INSTANCE == null) {
                synchronized(AppDatabase::class.java) {
                    if (RECIPE_INSTANCE == null) {
                        RECIPE_INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "database-cocktail").build()
                    }
                }
            }
            return RECIPE_INSTANCE!!
        }
    }


}
