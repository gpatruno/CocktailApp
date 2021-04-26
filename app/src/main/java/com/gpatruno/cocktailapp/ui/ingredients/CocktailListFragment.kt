package com.gpatruno.cocktailapp.ui.ingredients

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpatruno.cocktailapp.CocktailAdapter
import com.gpatruno.cocktailapp.data.CocktailList
import com.gpatruno.cocktailapp.data.CocktailListByIngredients
import com.gpatruno.cocktailapp.databinding.FragmentCocktailListBinding
import com.gpatruno.cocktailapp.databinding.FragmentDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import splitties.toast.toast

class CocktailListFragment : Fragment() {

    private var _binding: FragmentCocktailListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailListBinding.inflate(inflater, container, false)
        binding.CocktailRecyclerViewIng.layoutManager = LinearLayoutManager(binding.root.context)

        val cocktailName = arguments?.getString("cocktail_name")

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CocktailListByIngredients::class.java)
        val call = service.getCocktailData(cocktailName.toString())

        call.enqueue(object : Callback<CocktailList> {
            override fun onResponse(call: Call<CocktailList>, response: Response<CocktailList>) {
                if (response.code() == 200) {
                    binding.CocktailRecyclerViewIng.adapter = CocktailAdapter(response.body().cocktails.toTypedArray(), "ING")
                }
            }
            override fun onFailure(call: Call<CocktailList>, t: Throwable) {
                toast(t.message.toString())
            }
        })

        return binding.root
    }
}