package com.example.dogs.ui.base

import androidx.lifecycle.ViewModelProvider
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseActivity<V: BaseViewModel> : DaggerAppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var viewModel: V

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}