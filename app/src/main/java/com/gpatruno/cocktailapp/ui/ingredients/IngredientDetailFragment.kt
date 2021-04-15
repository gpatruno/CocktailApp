package com.gpatruno.cocktailapp.ui.ingredients

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gpatruno.cocktailapp.*
import com.gpatruno.cocktailapp.data.IngredientsBySearchList
import com.gpatruno.cocktailapp.data.IngredientsSearchService
import com.gpatruno.cocktailapp.databinding.FragmentIngredientDetailBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class IngredientDetailFragment : Fragment() {

    private lateinit var binding: FragmentIngredientDetailBinding
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredientDetailBinding.inflate(layoutInflater)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(IngredientsSearchService::class.java)
        val call = arguments?.getString("ingredient_name")?.let { service.getIngredientsSearch(it) }

        call?.enqueue(object : Callback<IngredientsBySearchList> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<IngredientsBySearchList>, response: Response<IngredientsBySearchList>) {
                if (response.code() == 200) {
                    val detailIngredient = response.body().IngredientSearch
                    val descriptionIngredient = detailIngredient[0].ingredientDescription
                    val ingredientAlcohol = detailIngredient[0].strAlcohol
                    val ingredientAlcoholPurcentage = detailIngredient[0].strABV
                    if (descriptionIngredient !== null) {
                        binding.ingredientDescription.text = descriptionIngredient.toString()
                    } else {
                        binding.ingredientDescription.text = "Aucune description disponible"
                    }

                    if (ingredientAlcohol !== null) {
                        binding.ingredientAlcool.setImageResource(R.drawable.ic_alcool)
                    } else {
                        binding.ingredientAlcool.setImageResource(R.drawable.ic_pas_d_alcool)
                    }

                    if(ingredientAlcoholPurcentage !== null) {
                        binding.purcentageAlcool.text= "$ingredientAlcoholPurcentage°"
                    } else {
                        binding.purcentageAlcool.text= "0°"
                    }


                }
            }

            override fun onFailure(call: Call<IngredientsBySearchList>, t: Throwable) {
                error(t.message.toString())
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ingredientName = arguments?.getString("ingredient_name")
        binding.ingredientsName.text = ingredientName
        val imgIngredient = binding.itemImageIngredientDetail
        Picasso.get().load("https://www.thecocktaildb.com/images/ingredients/$ingredientName-Medium.png").fit().into(imgIngredient)
    }


}