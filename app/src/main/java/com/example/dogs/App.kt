package com.example.dogs

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import com.example.dogs.di.component.DaggerAppComponent


class App : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out App> {
        return DaggerAppComponent.builder().create(this)
    }
}