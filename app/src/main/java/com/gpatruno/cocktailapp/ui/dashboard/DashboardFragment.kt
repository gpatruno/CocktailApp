package com.gpatruno.cocktailapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gpatruno.cocktailapp.data.CocktailList
import com.gpatruno.cocktailapp.data.CocktailListService
import com.gpatruno.cocktailapp.R
import com.gpatruno.cocktailapp.databinding.FragmentDashboardBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import splitties.toast.toast



class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding
    private var cocktailNumber = 0
    var fragment: Fragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)
        val textView: String = getString(R.string.title_dashboard)
        dashboardViewModel.text.observe(viewLifecycleOwner, Observer {
            textView
        })

        return binding.root
    }

    fun getData() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(CocktailListService::class.java)
        val call = service.getCocktailData()

        call.enqueue(object : Callback<CocktailList> {
            override fun onResponse(call: Call<CocktailList>, response: Response<CocktailList>) {
                if (response.code() == 200) {
                    cocktailNumber = response.body().cocktails.size
                    binding.cocktailCard.text =
                        cocktailNumber.toString() + " " + getString(R.string.cocktail)
                }
            }

            override fun onFailure(call: Call<CocktailList>, t: Throwable) {
                error(t.message.toString())
            }
        })
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        binding.cardDashboardCocktail.setOnClickListener{
            toast("Hello WORLD")
        }

        binding.ingredientsCard.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_navigation_dashboard_to_ingredients_fragment)
        }
        getData()
    }
}