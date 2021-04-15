package com.gpatruno.cocktailapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.gpatruno.cocktailapp.data.Cocktail
import com.gpatruno.cocktailapp.ui.features.FeaturesFragment
import com.gpatruno.cocktailapp.ui.instruction.InstructionFragment

class PageAdapter(fm: FragmentManager, var totalTabs: Int, var cocktail: Cocktail) : FragmentStatePagerAdapter(fm, FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return totalTabs
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FeaturesFragment(cocktail.category.toString(), cocktail.glass.toString())
            }
            1 -> {
                InstructionFragment(cocktail.instruction.toString())
            }

            else -> FeaturesFragment(cocktail.category.toString(), cocktail.glass.toString())
        }
    }
}