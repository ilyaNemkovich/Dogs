package com.example.dogs.di.component

import com.example.dogs.App
import com.example.dogs.di.annotations.ApplicationScope
import com.example.dogs.di.module.ActivityModule
import com.example.dogs.di.module.ApplicationModule
import com.example.dogs.di.module.NetworkModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}