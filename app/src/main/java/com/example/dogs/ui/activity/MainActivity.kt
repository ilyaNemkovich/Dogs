package com.example.dogs.ui.activity

import android.os.Bundle
import com.example.dogs.R
import com.example.dogs.ui.base.BaseActivity
import com.example.dogs.ui.fragment.randomDogImage.RandomDogImageFragment

class MainActivity : BaseActivity<MainActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, RandomDogImageFragment.newInstance())
                .commit()
        }
    }

}
