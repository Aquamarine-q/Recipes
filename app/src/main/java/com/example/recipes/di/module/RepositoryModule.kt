package com.example.recipes.di.module

import com.example.recipes.data.repository.MainRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MainRepository(get())
    }
}