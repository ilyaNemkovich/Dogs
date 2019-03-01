package com.example.dogs.ui.fragment

import com.example.dogs.di.annotations.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RandomDogFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindFragmetn(): RandomDogImageFragment
}