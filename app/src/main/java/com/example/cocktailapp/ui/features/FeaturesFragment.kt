package com.example.cocktailapp.ui.features

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.cocktailapp.databinding.FragmentFeaturesBinding

class FeaturesFragment(var drinkCategory: String, var glassTitle: String) : Fragment() {

    private var _binding: FragmentFeaturesBinding? = null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeaturesBinding.inflate(inflater, container, false)

        binding.drinkCategory.text = drinkCategory
        binding.glassTitle.text = glassTitle

        return binding.root
    }
}