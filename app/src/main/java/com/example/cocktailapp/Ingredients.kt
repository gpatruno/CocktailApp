package com.example.cocktailapp

import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
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

data class IngredientsBySearchList(
        @SerializedName("ingredients")
        val IngredientSearch: List<IngredientSearch>
)

data class IngredientSearch(
        @SerializedName("idIngredient")
        var idIngredient: Int? = null,

        @SerializedName("strDescription")
        var ingredientDescription: String? = null,

        @SerializedName("strIngredient")
        var strIngredient: String? = null,

        @SerializedName("strType")
        var strType: String? = null,

        @SerializedName("strAlcohol")
        var strAlcohol: String? = null,

        @SerializedName("strABV")
        var strABV: Int? = null,

) {}




// List Ingredients : www.thecocktaildb.com/api/json/v1/1/list.php?i=list
interface IngredientsService {
    @GET("list.php")
    fun getIngredientsData(@Query("i") c: String= "list"): Call<IngredientsList>
}

//Search ingredient by name
// example : www.thecocktaildb.com/api/json/v1/1/search.php?i=vodka
interface IngredientsSearchService {

    abstract val ingredientDetail: String

    @GET("search.php")
    fun getIngredientsSearch(@Query("i") c: String = ingredientDetail): Call<IngredientsBySearchList>
}