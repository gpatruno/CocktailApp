package com.example.cocktailapp.data

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class IngredientsList(
    @SerializedName("drinks")
    val ingredients: List<Ingredient>
)

data class Ingredient(
    @SerializedName("strIngredient1")
    var name: String? = null,
    var measure: String? = null,
    ) {}

interface IngredientsService {
    @GET("list.php")
    fun getIngredientsData(@Query("i") c: String= "list"): Call<IngredientsList>
}