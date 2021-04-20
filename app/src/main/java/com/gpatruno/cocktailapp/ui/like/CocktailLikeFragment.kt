package com.gpatruno.cocktailapp.ui.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpatruno.cocktailapp.CocktailAdapter
import com.gpatruno.cocktailapp.R
import com.gpatruno.cocktailapp.databinding.FragmentCocktailLikeBinding
import com.gpatruno.cocktailapp.databinding.FragmentHomeBinding

class CocktailLikeFragment : Fragment() {

    private lateinit var cocktailLikeViewModel: CocktailLikeViewModel

    private var _binding: FragmentCocktailLikeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailLikeBinding.inflate(inflater, container, false)
        binding.CocktaiLikeRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        //
        //binding.CocktailRecyclerView.adapter = CocktailAdapter(response.body().cocktails.toTypedArray())

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cocktailLikeSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(binding.CocktaiLikeRecyclerView.adapter != null) {
                    var cocktailAdapter = binding.CocktaiLikeRecyclerView.adapter as CocktailAdapter
                    cocktailAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(binding.CocktaiLikeRecyclerView.adapter != null) {
                    var cocktailAdapter = binding.CocktaiLikeRecyclerView.adapter as CocktailAdapter
                    cocktailAdapter.filter.filter(newText)
                }
                return false
            }

        })

    }
}