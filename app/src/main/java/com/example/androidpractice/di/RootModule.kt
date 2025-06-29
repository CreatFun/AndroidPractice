package com.example.androidpractice.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.androidpractice.domain.model.ProfileEntity
import com.example.androidpractice.favourite.presentation.viewModel.FavouriteViewModel
import com.example.androidpractice.listWithDetails.data.mapper.MovieResponseToEntityMapper
import com.example.androidpractice.listWithDetails.data.repository.MoviesRepository
import com.example.androidpractice.listWithDetails.domain.repository.IMoviesRepository
import com.example.androidpractice.listWithDetails.presentation.viewModel.ListViewModel
import com.example.androidpractice.listWithDetails.presentation.viewModel.MovieDetailsViewModel
import com.example.androidpractice.profile.data.serializer.DataSourceProvider
import com.example.androidpractice.profile.domain.IProfileRepository
import com.example.androidpractice.profile.presentation.viewModel.EditProfileViewModel
import com.example.androidpractice.profile.presentation.viewModel.ProfileViewModel
import com.example.androidpractice.profile.repository.ProfileRepository
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

@RequiresApi(Build.VERSION_CODES.O)
val rootModule = module {

    single<IMoviesRepository> {MoviesRepository(get(), get(), get())}
    single<IProfileRepository> { ProfileRepository() }
    single { getDataStore(androidContext()) }

    viewModel { ListViewModel(get(), it.get()) }
    viewModel { MovieDetailsViewModel(get(), it.get(), it.get()) }
    viewModel { FavouriteViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
    viewModel { EditProfileViewModel(get()) }

    factory { MovieResponseToEntityMapper() }
    factory<DataStore<ProfileEntity>>(named("profile")) { DataSourceProvider(get()).provide() }

}

fun getDataStore(androidContext: Context): DataStore<Preferences> =
    PreferenceDataStoreFactory.create {
        androidContext.preferencesDataStoreFile("default")
    }