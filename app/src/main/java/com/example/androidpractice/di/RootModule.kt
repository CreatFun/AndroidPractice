package com.example.androidpractice.di

import com.example.androidpractice.listWithDetails.data.repository.MoviesRepository
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository
import com.example.androidpractice.listWithDetails.presentation.viewModel.ListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single<IMoviesRepository> {MoviesRepository()}
    viewModel { ListViewModel(get(), it.get()) }
}