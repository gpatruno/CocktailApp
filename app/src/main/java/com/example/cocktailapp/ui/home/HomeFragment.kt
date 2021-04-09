package com.example.cocktailapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailapp.AppDatabase
import com.example.cocktailapp.data.CocktailList
import com.example.cocktailapp.CocktailAdapter
import com.example.cocktailapp.data.CocktailData
import com.example.cocktailapp.data.CocktailListService
import com.example.cocktailapp.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.CocktailRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        val database = AppDatabase.getRecipeDatabase(this.requireContext())

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CocktailListService::class.java)
        val call = service.getCocktailData()

        call.enqueue(object : Callback<CocktailList> {
            override fun onResponse(call: Call<CocktailList>, response: Response<CocktailList>) {
                if (response.code() == 200) {
                    binding.CocktailRecyclerView.adapter = CocktailAdapter(response.body().cocktails.toTypedArray())
                    //val cocktailList = response.body()
                    //val items = ArrayList<CocktailData>()
                    //Thread {
                     //   for (cocktail in cocktailList.cocktails) {
                            //var item = CocktailData(cocktail.id!!,cocktail.name, cocktail.instruction, cocktail.glass, cocktail.tags, cocktail.img)
                            //items.add(item)
                     //   }
                        //database.clearAllTables()
                        //database.cocktailDao().insertAll(items)
                   // }.start()
                }
            }
            override fun onFailure(call: Call<CocktailList>, t: Throwable) {
                error(t.message.toString())
            }
        })

        return binding.root
    }
}