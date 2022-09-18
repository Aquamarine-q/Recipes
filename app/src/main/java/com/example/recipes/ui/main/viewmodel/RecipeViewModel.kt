package com.example.recipes.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.data.model.Recipes
import com.example.recipes.data.repository.MainRepository
import com.example.recipes.utils.NetworkHelper
import com.example.recipes.utils.Resource
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val mainRepository: MainRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {
    private val _recipes = MutableLiveData<Resource<Recipes>>()
    val recipes: LiveData<Resource<Recipes>>
        get() = _recipes

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                mainRepository.getRecipes().let {
                    if (it.isSuccessful) {
                        _recipes.postValue(Resource.success(it.body()))
                    } else _recipes.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else _recipes.postValue(Resource.error("No internet connection", null))
        }
    }
}