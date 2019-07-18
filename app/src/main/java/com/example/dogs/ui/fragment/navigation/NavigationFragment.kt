package com.example.dogs.ui.fragment.navigation

import android.os.Bundle
import androidx.transition.TransitionManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.dogs.R
import com.example.dogs.databinding.FragmentNavigationBinding
import com.example.dogs.ui.base.BaseFragment
import com.google.android.material.button.MaterialButton
import kotlinx.android.synthetic.main.fragment_navigation.*
import kotlin.reflect.KClass

class NavigationFragment : BaseFragment<NavigationViewModel>() {
    override fun getViewModelClass() = NavigationViewModel::class
    override val layoutId: Int get() = R.layout.fragment_navigation

    private lateinit var transitionsContainer: ViewGroup
    lateinit var image: ImageView
    lateinit var infoButton: MaterialButton
    lateinit var quizButton: MaterialButton

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        btnInfo.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigationFragment_to_randomDogImageFragment)
        }
        btnQuiz.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_navigationFragment_to_appInfoFragment)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var visible = true

        transitionsContainer = layout_container
        image = transitionsContainer.findViewById(R.id.imageView2)
        infoButton = transitionsContainer.findViewById(R.id.btnInfo)
        quizButton = transitionsContainer.findViewById(R.id.btnQuiz)

        image.setOnClickListener {
            TransitionManager.beginDelayedTransition(transitionsContainer)
            visible = !visible
            when (visible) {
                true -> {
                    infoButton.visibility = View.GONE
                    quizButton.visibility = View.GONE
                }
                false -> {
                    infoButton.visibility = View.VISIBLE
                    quizButton.visibility = View.VISIBLE
                }
            }
            }
    }

    override fun onResume() {
        super.onResume()
        TransitionManager.beginDelayedTransition(transitionsContainer)
        infoButton.visibility = View.VISIBLE
        quizButton.visibility = View.VISIBLE
    }
}

