package com.example.cocktailapp.ui.ingredients

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cocktailapp.*
import com.example.cocktailapp.data.Ingredient
import com.example.cocktailapp.data.IngredientsList
import com.example.cocktailapp.data.IngredientsService
import com.example.cocktailapp.databinding.FragmentIngredientsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding
    private lateinit var test : ArrayList<Ingredient>
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredientsBinding.inflate(layoutInflater)
        binding.IngredientRecyclerView.layoutManager = GridLayoutManager(binding.root.context,2)

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        val service = retrofit.create(IngredientsService::class.java)
        val call = service.getIngredientsData()

        call.enqueue(object : Callback<IngredientsList> {
            override fun onResponse(call: Call<IngredientsList>, response: Response<IngredientsList>) {
                if (response.code() == 200) {
                    test = response.body().ingredients as ArrayList<Ingredient>
                    binding.IngredientRecyclerView.adapter = IngredientsAdapter(response.body().ingredients as ArrayList<Ingredient>)
                }
            }
            override fun onFailure(call: Call<IngredientsList>, t: Throwable) {
                error(t.message.toString())
            }
        })


        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.ingredientSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                IngredientsAdapter(test).filter.filter(newText)
                return false
            }

        })

    }



}