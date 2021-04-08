package com.example.cocktailapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapterIngredients(var items: Array<Ingredients>) : RecyclerView.Adapter<RecyclerAdapterIngredients.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindIngredientsItem(item: Ingredients){
            itemView.findViewById<CardView>(R.id.cardViewIngredients).setOnClickListener {
                Log.i(ViewHolder::class.simpleName, item.name.toString())
            }

            with(item) {
                val imgView = itemView.findViewById<ImageView>(R.id.item_image_ingredients)
                itemView.findViewById<TextView>(R.id.item_title_ingredients).text = item.name
                Picasso.get().load("https://www.thecocktaildb.com/images/ingredients/"+item.name+"-Medium.png").fit().into(imgView)
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerAdapterIngredients.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_ingredients, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindIngredientsItem(items[position])
    }



    override fun getItemCount(): Int {
        return items.size
    }

}