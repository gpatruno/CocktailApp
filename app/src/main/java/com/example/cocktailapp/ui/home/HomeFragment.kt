package com.example.cocktailapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cocktailapp.CocktailList
import com.example.cocktailapp.CoktailService
import com.example.cocktailapp.RecyclerAdapter
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

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CoktailService::class.java)
        val call = service.getCocktailData()

        call.enqueue(object : Callback<CocktailList> {
            override fun onResponse(call: Call<CocktailList>, response: Response<CocktailList>) {
                if (response.code() == 200) {
                    binding.CocktailRecyclerView.adapter = RecyclerAdapter(response.body().cocktails.toTypedArray())
                    println("cocktail number" + response.body().cocktails.size)
                }
            }
            override fun onFailure(call: Call<CocktailList>, t: Throwable) {
                error(t.message.toString())
            }
        })

        return binding.root
    }
}