package com.example.dogs.ui.fragment.appInfo

import com.example.dogs.di.annotations.FragmentScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppInfoFragmentProvider {
    @FragmentScope
    @ContributesAndroidInjector
    abstract fun bindFragmetn(): AppInfoFragment
}