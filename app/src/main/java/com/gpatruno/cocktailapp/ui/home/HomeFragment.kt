package com.gpatruno.cocktailapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gpatruno.cocktailapp.data.CocktailList
import com.gpatruno.cocktailapp.CocktailAdapter
import com.gpatruno.cocktailapp.data.CocktailListService
import com.gpatruno.cocktailapp.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import splitties.toast.toast

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.CocktailRecyclerView.layoutManager = LinearLayoutManager(binding.root.context)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CocktailListService::class.java)
        val call = service.getCocktailData()

        call.enqueue(object : Callback<CocktailList> {
            override fun onResponse(call: Call<CocktailList>, response: Response<CocktailList>) {
                if (response.code() == 200) {
                    binding.CocktailRecyclerView.adapter = CocktailAdapter(response.body().cocktails.toTypedArray(), "HOME")
                }
            }
            override fun onFailure(call: Call<CocktailList>, t: Throwable) {
                toast(t.message.toString())
            }
        })

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cocktailSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if(binding.CocktailRecyclerView.adapter != null) {
                    var cocktailAdapter = binding.CocktailRecyclerView.adapter as CocktailAdapter
                    cocktailAdapter.filter.filter(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(binding.CocktailRecyclerView.adapter != null) {
                    var cocktailAdapter = binding.CocktailRecyclerView.adapter as CocktailAdapter
                    cocktailAdapter.filter.filter(newText)
                }
                return false
            }

        })

    }
}