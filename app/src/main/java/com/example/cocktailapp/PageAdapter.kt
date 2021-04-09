package com.example.cocktailapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.cocktailapp.data.Cocktail
import com.example.cocktailapp.ui.features.FeaturesFragment
import com.example.cocktailapp.ui.instruction.InstructionFragment

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