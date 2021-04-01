package com.example.cocktailapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cocktailapp.*
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
                        Log.i(DetailFragment::class.simpleName, cocktails[0].name.toString())
                        binding.cocktailTitle.text = cocktails[0].name
                        binding.glassTitle.text = cocktails[0].glass
                        binding.drinkCategory.text = cocktails[0].category
                        if (cocktails[0].IBA.isNullOrEmpty()) {
                            binding.drinkIBA.visibility = View.INVISIBLE
                        } else {
                            binding.drinkIBA.visibility = View.VISIBLE
                            binding.drinkIBA.text = cocktails[0].IBA
                        }
                        val imgView = binding.cocktailImg
                        Picasso.get().load(cocktails[0].img).fit().into(imgView)
                        val tags = cocktails[0].tags.toString()
                        val arrayTags = tags.split(",")
                        mockData(cocktails[0])
                    }
                }
            }
            override fun onFailure(call: Call<CocktailList>, t: Throwable) {
                error(t.message.toString())
            }
        })

        return binding.root
    }

    fun mockData(item: Cocktail) {
        val list: MutableList<String>
        if (!item.ingredient1.isNullOrEmpty()) {
            list.add(item.ingredient1.toString())
        }
    }
}