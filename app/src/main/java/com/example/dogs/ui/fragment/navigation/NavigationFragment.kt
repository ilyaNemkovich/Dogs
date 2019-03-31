package com.example.dogs.ui.fragment.navigation

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.dogs.R
import com.example.dogs.databinding.FragmentNavigationBinding
import com.example.dogs.ui.base.BaseFragment

class NavigationFragment: BaseFragment<FragmentNavigationBinding, NavigationViewModel>() {

    override val viewModel: NavigationViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(NavigationViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.fragment_navigation

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewDataBinding.btnQuiz.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigationFragment_to_randomDogImageFragment)}
        viewDataBinding.btnInfo.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigationFragment_to_appInfoFragment)}
    }
}

