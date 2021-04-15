package com.gpatruno.cocktailapp.data

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
    var id: Int? = null,

    @SerializedName("strInstructions")
    var instruction: String? = null,

    @SerializedName("strCategory")
    var category: String? = null,

    @SerializedName("strIBA")
    var IBA: String? = null,

    @SerializedName("strGlass")
    var glass: String? = null,

    @SerializedName("strTags")
    var tags: String? = null,

    @SerializedName("strMeasure1")
    var measure1: String? = null,

    @SerializedName("strMeasure2")
    var measure2: String? = null,

    @SerializedName("strMeasure3")
    var measure3: String? = null,

    @SerializedName("strMeasure4")
    var measure4: String? = null,

    @SerializedName("strMeasure5")
    var measure5: String? = null,

    @SerializedName("strMeasure6")
    var measure6: String? = null,

    @SerializedName("strMeasure7")
    var measure7: String? = null,

    @SerializedName("strMeasure8")
    var measure8: String? = null,

    @SerializedName("strMeasure9")
    var measure9: String? = null,

    @SerializedName("strMeasure10")
    var measure10: String? = null,

    @SerializedName("strIngredient1")
    var ingredient1: String? = null,

    @SerializedName("strIngredient2")
    var ingredient2: String? = null,

    @SerializedName("strIngredient3")
    var ingredient3: String? = null,

    @SerializedName("strIngredient4")
    var ingredient4: String? = null,

    @SerializedName("strIngredient5")
    var ingredient5: String? = null,

    @SerializedName("strIngredient6")
    var ingredient6: String? = null,

    @SerializedName("strIngredient7")
    var ingredient7: String? = null,

    @SerializedName("strIngredient8")
    var ingredient8: String? = null,

    @SerializedName("strIngredient9")
    var ingredient9: String? = null,

    @SerializedName("strIngredient10")
    var ingredient10: String? = null,
    ) {}

interface CocktailListService {
    @GET("filter.php")
    fun getCocktailData(@Query("c") c: String= "Cocktail"): Call<CocktailList>
}

interface CocktailInfoService {
    @GET("lookup.php")
    fun getCocktailData(@Query("i") i: String): Call<CocktailList>
}