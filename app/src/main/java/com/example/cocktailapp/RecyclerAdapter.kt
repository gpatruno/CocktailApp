package com.example.cocktailapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class RecyclerAdapter(var items: Array<Cocktail>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindCocktailItem(item: Cocktail){
            itemView.findViewById<CardView>(R.id.cardView).setOnClickListener {
                Log.i(ViewHolder::class.simpleName, item.name.toString())
            }

            with(item) {
                val imgView = itemView.findViewById<ImageView>(R.id.item_image)
                Picasso.get().load(item.img).fit().into(imgView)
                itemView.findViewById<TextView>(R.id.item_title).text = item.name
                itemView.findViewById<TextView>(R.id.item_id).text = item.id.toString()
            }
        }

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_cocktail, viewGroup, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindCocktailItem(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

}