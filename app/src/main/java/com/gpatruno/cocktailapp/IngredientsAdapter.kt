package com.gpatruno.cocktailapp

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gpatruno.cocktailapp.data.Ingredient
import com.gpatruno.cocktailapp.databinding.ItemIngredientsBinding
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class IngredientsAdapter(private var items: Array<Ingredient>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(), Filterable{

    var ingredientsFilterList :Array<Ingredient> = arrayOf()

    init {
        ingredientsFilterList = items
    }

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
        viewHolder.bindIngredientsItem(ingredientsFilterList[position])
    }

    override fun getItemCount(): Int {
        return ingredientsFilterList.size
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            // Check if the user type a text in the SearchView
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    ingredientsFilterList = items
                } else {
                    val resultList = ArrayList<Ingredient>()
                    for(row in items) {
                        println("ROW"+row.name.toString())
                        println("CHAR"+charSearch.toLowerCase())

                        if (row.name.toString().toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(
                                Locale.ROOT))) {
                            resultList.add(row)
                            println("RESULT LIST  $resultList")
                        }
                    }
                    ingredientsFilterList = resultList.toTypedArray()
                }
                val filterResults = FilterResults()
                filterResults.values = ingredientsFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ingredientsFilterList.toMutableList().clear()
                ingredientsFilterList = results?.values as Array<Ingredient>
                notifyDataSetChanged()
            }

        }
    }
}