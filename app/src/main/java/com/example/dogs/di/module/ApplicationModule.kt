package com.example.dogs.di.module

import android.content.Context
import com.example.dogs.App
import com.example.dogs.di.annotations.ApplicationScope
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {
    @ApplicationScope
    @Binds
    abstract fun bindApplicationContext(application: App): Context
}