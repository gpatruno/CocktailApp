package com.example.cocktailapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.databinding.ItemCocktailBinding
import com.squareup.picasso.Picasso

class CocktailAdapter(var items: Array<Cocktail>) : RecyclerView.Adapter<CocktailAdapter.ViewHolder>() {

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
        viewHolder.bindCocktailItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }
}