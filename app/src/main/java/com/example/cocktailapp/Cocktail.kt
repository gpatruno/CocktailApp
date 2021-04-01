package com.example.cocktailapp

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

data class CocktailList(
    @SerializedName("drinks")
    val cocktails: List<Cocktail>
)

data class Cocktail(
    @SerializedName("strDrink")
    var name: String? = null,

    @SerializedName("strDrinkThumb")
    var img: String? = null,

    @SerializedName("idDrink")
    var id: Int? = null
    ) {}

interface CoktailService {
    @GET("filter.php")
    fun getCocktailData(@Query("c") c: String= "Cocktail"): Call<CocktailList>
}