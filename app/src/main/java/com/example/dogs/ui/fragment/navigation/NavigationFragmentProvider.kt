package com.example.dogs.ui.fragment.navigation

import com.example.dogs.di.annotations.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NavigationFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindFragmetn(): NavigationFragment
}