package com.gpatruno.cocktailapp.ui.dashboard

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gpatruno.cocktailapp.data.IngredientsList
import com.gpatruno.cocktailapp.data.IngredientsService
import com.gpatruno.cocktailapp.databinding.FragmentDashboardBinding
import com.gpatruno.cocktailapp.data.CocktailList
import com.gpatruno.cocktailapp.data.CocktailListService
import com.gpatruno.cocktailapp.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import splitties.alertdialog.appcompat.cancelButton
import splitties.alertdialog.material.materialAlertDialog
import splitties.toast.toast

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var binding: FragmentDashboardBinding
    private var cocktailNumber = 0
    private var ingredientsNumber = 0

    val websites = arrayOf("TheCocktailDB")

    lateinit var websiteSelect:String

    var fragment: Fragment? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(layoutInflater)
        dashboardViewModel = ViewModelProvider(this).get(DashboardViewModel::class.java)

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
                    binding.cocktailNumber.text = cocktailNumber.toString()
                }
            }

            override fun onFailure(call: Call<CocktailList>, t: Throwable) {
                error(t.message.toString())
            }
        })
    }

    fun getDataIngredient() {
        val retrofit = Retrofit.Builder()
                .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()


        val service = retrofit.create(IngredientsService::class.java)
        val call = service.getIngredientsData()

        call.enqueue(object : Callback<IngredientsList> {
            override fun onResponse(call: Call<IngredientsList>, response: Response<IngredientsList>) {
                if (response.code() == 200) {
                    ingredientsNumber = response.body().ingredients.size
                    binding.ingredientsNumber.text =  ingredientsNumber.toString()
                }
            }

            override fun onFailure(call: Call<IngredientsList>, t: Throwable) {
                error(t.message.toString())
            }
        })
    }

    private fun browse(url: String) {
        var browser = Intent(Intent.ACTION_VIEW, Uri.parse("https://$url"))
        startActivity(browser)
    }

    // Show Alert dialog with access to cocktail api database website
    private fun showAlertDialogWebsite() {
        context?.materialAlertDialog {
            setTitle(R.string.alert_dialog_title_website)
            setItems(websites) {
                _, which ->
                when(websites[which]) {
                    "TheCocktailDB" -> websiteSelect = getString(R.string.the_cocktail_db_website)
                }
                browse(websiteSelect)
            }
            cancelButton()
        }?.show()
    }

    // Send email
    private fun sendEmail(to: String, subject: String, msg: String) {
        val emailIntent = Intent(Intent.ACTION_SEND)

        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(to))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, msg)

        try {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.title_send_email)))
        } catch (ex: ActivityNotFoundException) {
            toast(R.string.text_no_email_client)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //(requireActivity() as AppCompatActivity).supportActionBar?.hide()
        binding.cardDashboardCocktail.setOnClickListener{
            toast("A lot of cocktail are available ! ")
        }

        binding.accessWebsite.setOnClickListener {
            showAlertDialogWebsite()
        }

        binding.sendEmail.setOnClickListener{
            sendEmail("bunsarak.pen@ynov.com", "Hello CocktailApp", "Message send to CocktailApp developper")
        }


        binding.ingredientsCard.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_navigation_dashboard_to_ingredients_fragment)
        }
        getData()
        getDataIngredient()
    }
}