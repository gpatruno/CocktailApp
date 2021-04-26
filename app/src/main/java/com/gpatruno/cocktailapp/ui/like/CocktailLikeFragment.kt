package com.gpatruno.cocktailapp.ui.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpatruno.cocktailapp.AppDatabase
import com.gpatruno.cocktailapp.CocktailAdapter
import com.gpatruno.cocktailapp.data.Cocktail
import com.gpatruno.cocktailapp.data.CocktailData
import com.gpatruno.cocktailapp.databinding.FragmentCocktailLikeBinding

class CocktailLikeFragment : Fragment() {

    private var _binding: FragmentCocktailLikeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCocktailLikeBinding.inflate(inflater, container, false)
        binding.CocktaiLikeRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = AppDatabase.getRecipeDatabase(this.requireContext())

        Thread {
            val lstCocktailData: List<CocktailData> = database.cocktailDao().getAll().toList()
            val lstCocktail = mutableListOf<Cocktail>()

            lstCocktailData.forEach {
                var item = Cocktail(it.name, it.img, it.id, it.instruction, null, null, it.glass, it.tags)
                lstCocktail.add(item)
                binding.CocktaiLikeRecyclerView.adapter = CocktailAdapter(lstCocktail.toTypedArray(), "LIKE")
            }
        }.start()

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