package com.example.cocktailapp.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CocktailDao {
    @Query("SELECT * FROM CocktailData")
    fun getAll(): List<CocktailData>

    @Query("SELECT * FROM CocktailData WHERE uid IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<CocktailData>

    @Query("SELECT * FROM user WHERE first_name LIKE :first AND " + "last_name LIKE :last LIMIT 1")
    fun findByName(first: String, last: String): CocktailData

    @Insert
    fun insertAll(vararg users: CocktailData)

    @Delete
    fun delete(user: CocktailData)
}