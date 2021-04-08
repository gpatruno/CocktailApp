package com.example.cocktailapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.databinding.ItemCocktailBinding
import com.example.cocktailapp.databinding.ItemIngredientCocktailBinding
import com.example.cocktailapp.databinding.ItemIngredientsBinding
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class RecyclerAdapterIngredients(var items: Array<Ingredients>) : RecyclerView.Adapter<RecyclerAdapterIngredients.ViewHolder>() {

    inner class ViewHolder(private val binding : ItemIngredientsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindIngredientsItem(item: Ingredients){
            binding.cardViewIngredients.setOnClickListener {
                val navController = Navigation.findNavController(binding.root)
                val bundle = bundleOf("ingredient_name" to item.name)
                navController.navigate(R.id.navigation_ingredients_detail, bundle)
            }


                val imgView = binding.itemImageIngredients
                binding.itemTitleIngredients.text = item.name
                Picasso.get().load("https://www.thecocktaildb.com/images/ingredients/"+item.name+"-Medium.png").fit().into(imgView)

        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerAdapterIngredients.ViewHolder {
        val binding = ItemIngredientsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindIngredientsItem(items[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }


}