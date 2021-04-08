package com.example.cocktailapp

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cocktailapp.databinding.ItemIngredientCocktailBinding

class ItemIngAdapter(var items: MutableList<String>): RecyclerView.Adapter<ItemIngAdapter.ViewHolder>(),
    ListAdapter {

    inner class ViewHolder(private val binding: ItemIngredientCocktailBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindIngItem(item: String){
            binding.nameIngredient.text = item
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemIngAdapter.ViewHolder {
        val binding = ItemIngredientCocktailBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemIngAdapter.ViewHolder, position: Int) {
        holder.bindIngItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun registerDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(p0: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): String {
        return items[p0]
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        return items.isEmpty()
    }

    override fun areAllItemsEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(p0: Int): Boolean {
        TODO("Not yet implemented")
    }
}