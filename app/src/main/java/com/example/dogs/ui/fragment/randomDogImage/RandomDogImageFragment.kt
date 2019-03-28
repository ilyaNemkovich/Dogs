package com.example.dogs.ui.fragment.randomDogImage

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
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
                    if (value.isAnswer) {
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
            layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@RandomDogImageFragment.context)
                .apply { recycleChildrenOnDetach = true }
            adapter = recyclerAdapter
        }
    }

    override fun onItemClick(view: View, randomImageQuiz: RandomImageQuiz) {
        when (randomImageQuiz.isAnswer){
            true -> Toast.makeText(context, "true", Toast.LENGTH_LONG).show()
            false -> Toast.makeText(context, "false", Toast.LENGTH_LONG).show()
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