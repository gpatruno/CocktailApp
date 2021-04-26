package com.gpatruno.cocktailapp.ui.ingredients

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.gpatruno.cocktailapp.*
import com.gpatruno.cocktailapp.data.IngredientsList
import com.gpatruno.cocktailapp.data.IngredientsService
import com.gpatruno.cocktailapp.databinding.FragmentIngredientsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import splitties.toast.toast

class IngredientsFragment : Fragment() {

    private lateinit var binding: FragmentIngredientsBinding

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
                    binding.IngredientRecyclerView.adapter = IngredientsAdapter(response.body().ingredients.toTypedArray(), true)
                }
            }
            override fun onFailure(call: Call<IngredientsList>, t: Throwable) {
                toast(t.message.toString())
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ingredientSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(binding.IngredientRecyclerView.adapter != null) {
                    var ingredientAdapter = binding.IngredientRecyclerView.adapter as IngredientsAdapter
                    ingredientAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(binding.IngredientRecyclerView.adapter != null) {
                    var ingredientAdapter = binding.IngredientRecyclerView.adapter as IngredientsAdapter
                    ingredientAdapter.filter.filter(newText)
                }
                return false
            }
        })
    }
}