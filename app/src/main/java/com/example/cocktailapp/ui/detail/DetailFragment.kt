package com.example.cocktailapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.cocktailapp.*
import com.example.cocktailapp.databinding.FragmentDetailBinding
import com.google.gson.Gson
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
                        Log.i(DetailFragment::class.simpleName, cocktails[0].name.toString())
                        //val hashMap: MutableMap<String, String> = Gson().fromJson(cocktails[0], HashMap::class.java)
                        binding.cocktailTitle.text = cocktails[0].name
                        binding.glassTitle.text = cocktails[0].glass
                        binding.drinkCategory.text = cocktails[0].category
                        binding.drinkIBA.visibility = if (cocktails[0].IBA.isNullOrEmpty()) View.INVISIBLE else View.VISIBLE
                        binding.drinkIBA.text = cocktails[0].IBA
                        Picasso.get().load(cocktails[0].img).fit().into(binding.cocktailImg)
                        val list: MutableList<String> = mockData(cocktails[0])
                        //val arrayTags = cocktails[0].tags.toString().split(",")
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
        if (!item.ingredient2.isNullOrEmpty()) {
            list.add(item.ingredient2.toString())
        } else return list
        if (!item.ingredient2.isNullOrEmpty()) {
            list.add(item.ingredient2.toString())
        } else return list
        if (!item.ingredient2.isNullOrEmpty()) {
            list.add(item.ingredient2.toString())
        } else return list
        if (!item.ingredient2.isNullOrEmpty()) {
            list.add(item.ingredient2.toString())
        } else return list
        if (!item.ingredient2.isNullOrEmpty()) {
            list.add(item.ingredient2.toString())
        } else return list



        Log.i(DetailFragment::class.simpleName, list[1])

        return list
    }
}