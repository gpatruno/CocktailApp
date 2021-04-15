package com.gpatruno.cocktailapp.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.GridLayoutManager
import com.gpatruno.cocktailapp.*
import com.gpatruno.cocktailapp.data.*
import com.gpatruno.cocktailapp.databinding.FragmentDetailBinding
import com.google.android.material.tabs.TabLayout
import com.gpatruno.cocktailapp.data.Ingredient
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
        binding.lstIngredients.layoutManager = GridLayoutManager(binding.root.context,2)

        val cocktailId = arguments?.getInt("cocktail_id").toString()

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Features"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Instruction"))
        binding.viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout))
        val fmManager : FragmentManager = this.parentFragmentManager

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }
            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })

        val database = AppDatabase.getRecipeDatabase(this.requireContext())

        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(CocktailInfoService::class.java)
        val call = service.getCocktailData(cocktailId)

        Log.i(DetailFragment::class.java.simpleName, "AVANT RETROFIT = " + cocktailId)
        call.enqueue(object : Callback<CocktailList> {
            override fun onResponse(call: Call<CocktailList>, response: Response<CocktailList>) {
                if (response.code() == 200) {
                    val cocktails = response.body().cocktails
                    if (cocktails.isNotEmpty() && cocktails.size == 1) {
                        binding.viewPager.adapter = PageAdapter(fmManager, binding.tabLayout.tabCount, cocktails[0])
                        binding.viewPager.adapter?.notifyDataSetChanged()
                        binding.cocktailTitle.text = cocktails[0].name
                        Picasso.get().load(cocktails[0].img).fit().into(binding.cocktailImg)
                        binding.lstIngredients.adapter = IngredientsAdapter(mockData(cocktails[0]).toTypedArray(), false)
                        // On met le Cocktail dans la BDD
                        Thread {
                           for (cocktail in cocktails) {
                                val item = CocktailData(null, cocktail.id, cocktail.name, cocktail.instruction, cocktail.glass, cocktail.tags, cocktail.img)
                               database.cocktailDao().insert(item)
                           }
                        }.start()
                    }
                }
            }

            override fun onFailure(call: Call<CocktailList>, t: Throwable) {
                error(t.message.toString())
            }
        })
        return binding.root
    }

    fun mockData(item: Cocktail): List<Ingredient>  {
        val list: MutableList<Ingredient> = mutableListOf()

        if (!item.ingredient1.isNullOrEmpty()) {
            list.add(Ingredient(item.ingredient1, item.measure1))
        } else return list
        if (!item.ingredient2.isNullOrEmpty()) {
            list.add(Ingredient(item.ingredient2, item.measure2))
        } else return list
        if (!item.ingredient3.isNullOrEmpty()) {
            list.add(Ingredient(item.ingredient3, item.measure3))
        } else return list
        if (!item.ingredient4.isNullOrEmpty()) {
            list.add(Ingredient(item.ingredient4, item.measure4))
        } else return list
        if (!item.ingredient5.isNullOrEmpty()) {
            list.add(Ingredient(item.ingredient5, item.measure5))
        } else return list
        if (!item.ingredient6.isNullOrEmpty()) {
            list.add(Ingredient(item.ingredient6, item.measure6))
        } else return list
        if (!item.ingredient7.isNullOrEmpty()) {
            list.add(Ingredient(item.ingredient7, item.measure7))
        } else return list
        if (!item.ingredient8.isNullOrEmpty()) {
            list.add(Ingredient(item.ingredient8, item.measure8))
        } else return list
        if (!item.ingredient9.isNullOrEmpty()) {
            list.add(Ingredient(item.ingredient9, item.measure9))
        } else return list
        if (!item.ingredient10.isNullOrEmpty()) {
            list.add(Ingredient(item.ingredient10, item.measure10))
        }

        return list
    }
}