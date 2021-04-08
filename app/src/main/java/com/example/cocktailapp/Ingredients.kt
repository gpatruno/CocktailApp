package com.example.cocktailapp

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class IngredientsList(
    @SerializedName("drinks")
    val ingredients: List<Ingredients>
)

data class Ingredients(
    @SerializedName("strIngredient1")
    var name: String? = null,
    var img: String? = null,

    ) {}



interface IngredientsService {
    @GET("list.php")
    fun getIngredientsData(@Query("i") c: String= "list"): Call<IngredientsList>
}

interface IngredientsImagesService {
    @GET("list.php")
    fun getIngredientsImageData(@Query("i") c: String= "list"): Call<IngredientsList>
}