package com.example.dogs.ui.fragment.randomDogImage

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory
import com.example.dogs.R
import com.example.dogs.ui.base.BaseFragment
import com.example.dogs.ui.fragment.randomDogImage.adapter.RandomDogQuizAdapter
import com.example.dogs.ui.fragment.randomDogImage.data.RandomImageQuiz
import kotlinx.android.synthetic.main.fragment_random_dog.*

class RandomDogImageFragment : BaseFragment<RandomDogImageViewModel>(),
    RandomDogQuizAdapter.OnItemClickListener {

    override fun getViewModelClass() = RandomDogImageViewModel::class
    override val layoutId: Int get() = R.layout.fragment_random_dog

    private var recyclerAdapter = RandomDogQuizAdapter(this)
    private val pvhX = PropertyValuesHolder.ofFloat("scaleX", 1f, 1.4f, 1f)
    private val pvhY = PropertyValuesHolder.ofFloat("scaleY", 1f, 1.4f, 1f)

    private var bread = "bread"

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        setupViewListeners()
        subscribeToLiveData()
    }

    private fun setupViewListeners() {
        fl_refresh.setOnClickListener {
            viewModel.changeImage()
        }
    }

    @SuppressLint("RestrictedApi")
    private fun subscribeToLiveData() {
        viewModel.mutableRandomImageResponse.observe(this, Observer {
            if (it != null) {
                tv_right_answ.text = viewModel.rightAnswers.toString()
                tv_wrong_answ.text = viewModel.wrongAnswers.toString()
                recyclerAdapter.setItems(it)
                bread = it.first { randomImageQuiz -> randomImageQuiz.isCorrectAnswer }.breed
                shimmerLayout.visibility = View.INVISIBLE
            }
        })
        viewModel.mutableCurrentImage.observe(this, Observer {
            if (it != null) {
                fl_refresh.visibility = View.VISIBLE
                Glide.with(imageView)
                    .load(it)
                    .apply(options)
                    .into(imageView)
            } else {
                fl_refresh.visibility = View.GONE
            }
        })
    }

    private fun setupView() {
        recyclerView.apply {
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@RandomDogImageFragment.context)
                .apply { recycleChildrenOnDetach = true }
            adapter = recyclerAdapter
        }
    }

    override fun onItemClick(view: View, randomImageQuiz: RandomImageQuiz) {
        shimmerLayout.visibility = View.VISIBLE
        recyclerAdapter.clearList()
        when (randomImageQuiz.isCorrectAnswer) {
            true -> {
                viewModel.rightAnswers++
                tv_right_answ.text = viewModel.rightAnswers.toString()
                ObjectAnimator.ofPropertyValuesHolder(tv_right_answ, pvhX, pvhY).setDuration(700).start()
            }
            false -> {
                viewModel.wrongAnswers++
                tv_wrong_answ.text = viewModel.wrongAnswers.toString()
                ObjectAnimator.ofPropertyValuesHolder(tv_wrong_answ, pvhX, pvhY).setDuration(700).start()
            }
        }
        viewModel.loadRandomUrl()
    }

    override fun onResume() {
        super.onResume()
        shimmerLayout.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        shimmerLayout.stopShimmer()
    }

    companion object {
        fun newInstance() = RandomDogImageFragment().apply {
            arguments = Bundle().apply {
            }
        }

        val options = RequestOptions().apply {
            //            optionalCircleCrop()
            placeholder(R.drawable.dog_ph)
//            apply(RequestOptions().circleCrop())
            error(R.drawable.load_error_dog)
        }
    }
}