package com.example.dogs.di.module

import com.example.dogs.di.annotations.ActivityScope
import com.example.dogs.ui.activity.MainActivity
import com.example.dogs.ui.fragment.randomDogImage.RandomDogFragmentProvider
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [RandomDogFragmentProvider::class])
    abstract fun bindMainActivity(): MainActivity
}