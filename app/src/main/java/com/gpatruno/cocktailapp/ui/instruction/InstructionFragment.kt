package com.gpatruno.cocktailapp.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gpatruno.cocktailapp.databinding.FragmentInstructionBinding

class InstructionFragment(var instruction: String) : Fragment() {

    private var _binding: FragmentInstructionBinding? = null
    private  val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentInstructionBinding.inflate(inflater, container, false)
        binding.instruction.text = instruction

        return binding.root
    }
}