package com.example.cocktailapp.ui.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cocktailapp.R
import com.example.cocktailapp.databinding.FragmentIngredientDetailBinding
import com.squareup.picasso.Picasso


class IngredientDetailFragment : Fragment() {

    private lateinit var binding: FragmentIngredientDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredientDetailBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val ingredientName = arguments?.getString("ingredient_name")
        binding.ingredientsName.text = ingredientName
        val imgIngredient = binding.itemImageIngredientDetail
        Picasso.get().load("https://www.thecocktaildb.com/images/ingredients/$ingredientName.png").fit().into(imgIngredient)


    }


}