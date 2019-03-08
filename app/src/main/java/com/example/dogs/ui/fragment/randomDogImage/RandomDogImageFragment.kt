package com.example.dogs.ui.fragment.randomDogImage

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dogs.BR
import com.example.dogs.R
import com.example.dogs.databinding.LayoutRandomDogBinding
import com.example.dogs.ui.base.BaseFragment
import com.example.dogs.ui.fragment.randomDogImage.adapter.RandomDogQuizAdapter
import com.example.dogs.ui.fragment.randomDogImage.data.RandomImageQuiz
import org.jetbrains.anko.support.v4.toast

class RandomDogImageFragment : BaseFragment<LayoutRandomDogBinding, RandomDogImageViewModel>(), RandomDogQuizAdapter.OnItemClickListener {

    private var recyclerAdapter = RandomDogQuizAdapter(this)

    override val viewModel: RandomDogImageViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(RandomDogImageViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.layout_random_dog
    override val bindingVariables: Map<Int, Any>?
        get() = mapOf(Pair(BR.viewModel, viewModel))

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupView()
        subscribeToLiveData()
    }

    private fun subscribeToLiveData() {
        viewModel.mutableRandomImageResponse.observe(this, Observer {
            if (it != null) {
                for ((index, value) in it.withIndex())
                    if (value.question) {
                        Glide.with(this)
                            .load(it[(index)].imageUrl)
                            .apply(options)
                            .into(viewDataBinding.imageView)
                    }
                recyclerAdapter.setItems(it)
            }
        })

        viewModel.mutableBreadImagesResponse.observe(this, Observer {
            if (it != null){
                Glide.with(this)
                    .load(it.imageUrls[(0..(it.imageUrls.size-1)).random()])
                    .apply(options)
                    .into(viewDataBinding.imageView)
            }
        })
    }

    private fun setupView() {
        viewDataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RandomDogImageFragment.context)
                .apply { recycleChildrenOnDetach = true }
            adapter = recyclerAdapter
        }
    }

    override fun onItemClick(view: View, randomImageQuiz: RandomImageQuiz) {
        when (randomImageQuiz.question){
            true -> toast("true")
            false -> toast("false")
        }
        viewModel.loadRandomUrl()
    }

    companion object {
        fun newInstance() = RandomDogImageFragment().apply {
            arguments = Bundle().apply {
            }
        }

        val options = RequestOptions().apply {
            optionalCircleCrop()
            placeholder(R.drawable.place_holder_dog)
            error(R.drawable.load_error_dog)
            apply(RequestOptions().circleCrop())
        }
    }
}