package org.example.cmp.di

import org.example.cmp.data.network.createHttpClient
import org.example.cmp.data.remote.MealApiService
import org.example.cmp.data.remote.MealApiServiceImpl
import org.example.cmp.data.repositories.MealRepositoryImpl
import org.example.cmp.domain.repositories.MealRepository
import org.example.cmp.domain.usecases.GetMealDetailUseCase
import org.example.cmp.domain.usecases.GetMealsByCategoryUseCase
import org.example.cmp.presentation.screens.details.MealDetailViewModel
import org.example.cmp.presentation.screens.list.MealsListViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    // Network
    single { createHttpClient() }
    
    // Data Layer
    singleOf(::MealApiServiceImpl) { bind<MealApiService>() }
    singleOf(::MealRepositoryImpl) { bind<MealRepository>() }
    
    // Domain Layer (Use Cases)
    singleOf(::GetMealsByCategoryUseCase)
    singleOf(::GetMealDetailUseCase)
    
    // Presentation Layer (ViewModels)
    viewModel { MealsListViewModel(get()) }
    viewModel { (mealId: String) -> MealDetailViewModel(mealId, get()) }
} 