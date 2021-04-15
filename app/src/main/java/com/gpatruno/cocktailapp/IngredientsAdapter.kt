package com.gpatruno.cocktailapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gpatruno.cocktailapp.data.Ingredient
import com.gpatruno.cocktailapp.databinding.ItemIngredientsBinding
import com.squareup.picasso.Picasso

class IngredientsAdapter(private var items: Array<Ingredient>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemIngredientsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindIngredientsItem(item: Ingredient){
            binding.cardViewIngredients.setOnClickListener {
                Log.i(ViewHolder::class.simpleName, item.name.toString())
                val navController = Navigation.findNavController(binding.root)
                val bundle = bundleOf("ingredient_name" to item.name)
                navController.navigate(R.id.navigation_ingredients_detail, bundle)
            }

            binding.itemTitleIngredient.text = item.name
            binding.ItemMeasureIng.text = item.measure
            Picasso.get().load("https://www.thecocktaildb.com/images/ingredients/"+item.name+"-Medium.png").fit().into(binding.itemImageIngredients)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemIngredientsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return  ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindIngredientsItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}