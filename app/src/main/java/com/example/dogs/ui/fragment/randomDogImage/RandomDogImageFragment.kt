package com.example.dogs.ui.fragment.randomDogImage

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.example.dogs.BR
import com.example.dogs.R
import com.example.dogs.databinding.LayoutRandomDogBinding
import com.example.dogs.ui.base.BaseFragment

class RandomDogImageFragment : BaseFragment<LayoutRandomDogBinding, RandomDogImageViewModel>() {

    override val viewModel: RandomDogImageViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(RandomDogImageViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.layout_random_dog
    override val bindingVariables: Map<Int, Any>?
        get() = mapOf(Pair(BR.viewModel, viewModel))

    companion object {
        fun newInstance() = RandomDogImageFragment().apply {
            arguments = Bundle().apply {
            }
        }
    }
}