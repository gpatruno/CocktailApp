package com.gpatruno.cocktailapp

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gpatruno.cocktailapp.data.Cocktail
import com.gpatruno.cocktailapp.databinding.ItemCocktailBinding
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class CocktailAdapter(var items: Array<Cocktail>) : RecyclerView.Adapter<CocktailAdapter.ViewHolder>(), Filterable {

    var cocktailsFilterList :Array<Cocktail> = arrayOf()

    init {
        cocktailsFilterList = items
    }

    inner class ViewHolder(private val binding: ItemCocktailBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindCocktailItem(item: Cocktail){
            binding.cardView.setOnClickListener {
                val navController = findNavController(binding.root)
                val bundle = bundleOf("cocktail_id" to item.id)
                navController.navigate(R.id.action_navigation_home_to_detail_cocktail, bundle)
            }

            Picasso.get().load(item.img).fit().into(binding.itemImage)
            binding.itemTitle.text = item.name
            binding.itemId.text = item.id.toString()
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCocktailBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindCocktailItem(cocktailsFilterList[position])
    }

    override fun getItemCount(): Int {
        return cocktailsFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            // Check if the user type a text in the SearchView
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    cocktailsFilterList = items
                } else {
                    val resultList = ArrayList<Cocktail>()
                    for(row in items) {
                        println("ROW"+row.name.toString())
                        println("CHAR"+charSearch.toLowerCase())

                        if (row.name.toString().toLowerCase(Locale.ROOT).contains(charSearch.toLowerCase(
                                        Locale.ROOT))) {
                            resultList.add(row)
                            println("RESULT LIST  $resultList")
                        }
                    }
                    cocktailsFilterList = resultList.toTypedArray()
                }
                val filterResults = FilterResults()
                filterResults.values = cocktailsFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                cocktailsFilterList.toMutableList().clear()
                cocktailsFilterList = results?.values as Array<Cocktail>
                notifyDataSetChanged()
            }

        }
    }
}