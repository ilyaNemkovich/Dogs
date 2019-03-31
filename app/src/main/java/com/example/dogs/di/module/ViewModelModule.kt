package com.example.dogs.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dogs.di.annotations.ViewModelKey
import com.example.dogs.ui.activity.MainActivityViewModel
import com.example.dogs.ui.fragment.navigation.NavigationViewModel
import com.example.dogs.ui.fragment.randomDogImage.RandomDogImageViewModel
import com.example.dogs.ui.utils.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    fun bindViewModuleFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun mainActivity(viewModel: MainActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RandomDogImageViewModel::class)
    fun randomDogImageFragment(viewModel: RandomDogImageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NavigationViewModel::class)
    fun navigationFragment(viewModel: NavigationViewModel): ViewModel
}
