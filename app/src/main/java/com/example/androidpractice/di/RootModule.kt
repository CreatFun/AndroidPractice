package com.example.androidpractice.di

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.androidpractice.listWithDetails.data.mapper.MovieResponseToEntityMapper
import com.example.androidpractice.listWithDetails.data.repository.MoviesRepository
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository
import com.example.androidpractice.listWithDetails.presentation.viewModel.ListViewModel
import com.example.androidpractice.listWithDetails.presentation.viewModel.MovieDetailsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val rootModule = module {
    single<IMoviesRepository> {MoviesRepository(get(), get())}
    viewModel { ListViewModel(get(), it.get()) }
    viewModel { MovieDetailsViewModel(get(), it.get(), it.get()) }
    factory { MovieResponseToEntityMapper() }
}