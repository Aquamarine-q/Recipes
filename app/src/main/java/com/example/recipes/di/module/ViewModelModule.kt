package com.example.recipes.di.module

import com.example.recipes.ui.main.viewmodel.MainViewModel
import com.example.recipes.ui.main.viewmodel.MostPopularRecipeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(get(),get())
    }
    viewModel {
        MostPopularRecipeViewModel(get(), get())
    }
}