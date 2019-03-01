package com.example.dogs.ui.activity

import android.os.Bundle
import com.example.dogs.R
import com.example.dogs.ui.base.BaseActivity
import com.example.dogs.ui.fragment.RandomDogImageFragment

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
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
