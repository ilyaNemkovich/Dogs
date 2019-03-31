package com.example.dogs.ui.fragment.randomDogImage

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dogs.BR
import com.example.dogs.R
import com.example.dogs.databinding.FragmentRandomDogBinding
import com.example.dogs.ui.base.BaseFragment
import com.example.dogs.ui.fragment.randomDogImage.adapter.RandomDogQuizAdapter
import com.example.dogs.ui.fragment.randomDogImage.data.RandomImageQuiz

class RandomDogImageFragment : BaseFragment<FragmentRandomDogBinding, RandomDogImageViewModel>(),
    RandomDogQuizAdapter.OnItemClickListener {

    private var recyclerAdapter = RandomDogQuizAdapter(this)

    override val viewModel: RandomDogImageViewModel
        get() = ViewModelProviders.of(this, viewModelFactory).get(RandomDogImageViewModel::class.java)
    override val layoutId: Int
        get() = R.layout.fragment_random_dog
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
                viewDataBinding.shimmerLayout.visibility = View.GONE
                viewDataBinding.recyclerView.visibility = View.VISIBLE
            }
        })

        viewModel.mutableBreadImagesResponse.observe(this, Observer {
            if (it != null) {
                Glide.with(this)
                    .load(it.imageUrls[(0..(it.imageUrls.size - 1)).random()])
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
        viewDataBinding.shimmerLayout.visibility = View.VISIBLE
        viewDataBinding.recyclerView.visibility = View.GONE
        when (randomImageQuiz.isAnswer) {
            true -> Toast.makeText(context, "true", Toast.LENGTH_LONG).show()
            false -> Toast.makeText(context, "false", Toast.LENGTH_LONG).show()
        }
        viewModel.loadRandomUrl()
    }

    override fun onResume() {
        super.onResume()
        viewDataBinding.shimmerLayout.startShimmerAnimation()
    }

    override fun onPause() {
        super.onPause()
        viewDataBinding.shimmerLayout.stopShimmerAnimation()
    }

    companion object {
        fun newInstance() = RandomDogImageFragment().apply {
            arguments = Bundle().apply {
            }
        }

        val options = RequestOptions().apply {
            optionalCircleCrop()
            placeholder(R.drawable.dog_ph)
            apply(RequestOptions().circleCrop())
            error(R.drawable.load_error_dog)
        }
    }
}