package com.example.dogs.ui.activity

import android.os.Bundle
import com.example.dogs.R
import com.example.dogs.ui.base.BaseActivity

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
