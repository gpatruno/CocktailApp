package com.example.cocktailapp.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cocktailapp.data.Cocktail
import com.example.cocktailapp.data.CocktailInfoService
import com.example.cocktailapp.data.CocktailList
import com.example.cocktailapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailFragment : Fragment(){

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val cocktailId = arguments?.getInt("cocktail_id").toString()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(CocktailInfoService::class.java)
        val call = service.getCocktailData(cocktailId)

        call.enqueue(object : Callback<CocktailList> {
            override fun onResponse(call: Call<CocktailList>, response: Response<CocktailList>) {
                if (response.code() == 200) {
                    val cocktails = response.body().cocktails
                    if (cocktails.isNotEmpty() && cocktails.size == 1) {
                        binding.cocktailTitle.text = cocktails[0].name
                        binding.glassTitle.text = cocktails[0].glass
                        binding.drinkCategory.text = cocktails[0].category
                        binding.drinkIBA.visibility = if (cocktails[0].IBA.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
                        binding.drinkIBA.text = cocktails[0].IBA
                        Picasso.get().load(cocktails[0].img).fit().into(binding.cocktailImg)
                        val list: MutableList<String> = mockData(cocktails[0])
                        //binding.lstIngredients.adapter = ItemIngAdapter(list)
                    }
                }
            }

            override fun onFailure(call: Call<CocktailList>, t: Throwable) {
                error(t.message.toString())
            }
        })
        return binding.root
    }

    fun mockData(item: Cocktail): MutableList<String>  {
        val list: MutableList<String> = mutableListOf()
        if (!item.ingredient1.isNullOrEmpty()) {
            list.add(item.ingredient1.toString())
        } else return list
        if (!item.ingredient2.isNullOrEmpty()) {
            list.add(item.ingredient2.toString())
        } else return list
        if (!item.ingredient3.isNullOrEmpty()) {
            list.add(item.ingredient3.toString())
        } else return list
        if (!item.ingredient4.isNullOrEmpty()) {
            list.add(item.ingredient4.toString())
        } else return list
        if (!item.ingredient5.isNullOrEmpty()) {
            list.add(item.ingredient5.toString())
        } else return list
        if (!item.ingredient6.isNullOrEmpty()) {
            list.add(item.ingredient6.toString())
        } else return list
        if (!item.ingredient7.isNullOrEmpty()) {
            list.add(item.ingredient7.toString())
        } else return list
        if (!item.ingredient8.isNullOrEmpty()) {
            list.add(item.ingredient8.toString())
        } else return list
        if (!item.ingredient9.isNullOrEmpty()) {
            list.add(item.ingredient9.toString())
        } else return list
        if (!item.ingredient10.isNullOrEmpty()) {
            list.add(item.ingredient10.toString())
        }

        return list
    }
}