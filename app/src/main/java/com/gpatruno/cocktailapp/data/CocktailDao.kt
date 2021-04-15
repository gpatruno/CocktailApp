package com.gpatruno.cocktailapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CocktailDao {
    @Query("SELECT * FROM CocktailData")
    fun getAll(): List<CocktailData>

    @Query("SELECT * FROM CocktailData WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<CocktailData>

    @Query("SELECT * FROM CocktailData WHERE name LIKE :name")
    fun findByName(name: String): CocktailData

    @Insert
    fun insertAll(vararg cocktails: CocktailData)

    @Delete
    fun delete(cocktail: CocktailData)
}