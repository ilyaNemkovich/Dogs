package com.example.dogs.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.dogs.R
import com.example.dogs.data.network.DogApi
import com.example.dogs.ui.base.BaseFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.layout_random_dog.*
import javax.inject.Inject

class RandomDogImageFragment : BaseFragment() {

    @Inject
    lateinit var dogApi: DogApi

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.layout_random_dog, container, false)

        dogApi.getRandomImage()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { response ->
                Glide.with(this)
                    .load(response.message)
                    .into(imageView)
            }

        return view
    }

    companion object {
        fun newInstance() = RandomDogImageFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}