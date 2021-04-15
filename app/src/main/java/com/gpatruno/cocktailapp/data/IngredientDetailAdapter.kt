package com.gpatruno.cocktailapp.data

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.gpatruno.cocktailapp.R
import com.gpatruno.cocktailapp.databinding.ItemIngredientsBinding
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class IngredientDetailAdapter(var items: ArrayList<Ingredient>) : RecyclerView.Adapter<IngredientDetailAdapter.ViewHolder>(), Filterable {
    var ingredientsFilterList = ArrayList<Ingredient>()

    init {
        ingredientsFilterList = items
    }
    inner class ViewHolder(private val binding : ItemIngredientsBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bindIngredientsItem(item: Ingredient){
            binding.cardViewIngredients.setOnClickListener {
                val navController = Navigation.findNavController(binding.root)
                val bundle = bundleOf("ingredient_name" to item.name)
                navController.navigate(R.id.navigation_ingredients_detail, bundle)
            }
                val imgView = binding.itemImageIngredients
                binding.itemTitleIngredient.text = item.name
                Picasso.get().load("https://www.thecocktaildb.com/images/ingredients/"+item.name+"-Medium.png").fit().into(imgView)

        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): IngredientDetailAdapter.ViewHolder {
        val binding = ItemIngredientsBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindIngredientsItem(items[position])
    }

    override fun getItemCount(): Int {
        return ingredientsFilterList.size
    }

    override fun getFilter(): Filter{
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

                        if (row.name.toString()?.toLowerCase(Locale.ROOT)?.contains(charSearch.toLowerCase(Locale.ROOT))) {
                            resultList.add(row)
                            println("RESULT LIST  $resultList")
                        }
                    }
                    ingredientsFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = ingredientsFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ingredientsFilterList = results?.values as ArrayList<Ingredient>
                notifyDataSetChanged()
            }

        }
    }



}