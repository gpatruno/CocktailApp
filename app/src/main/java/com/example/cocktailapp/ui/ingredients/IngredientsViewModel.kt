package com.example.cocktailapp.ui.ingredients

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class IngredientsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is IngredientsViewModel"
    }
    val text: LiveData<String> = _text
}