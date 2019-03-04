package com.example.dogs.ui.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.dogs.R

@BindingAdapter("photo_link")
fun setPhoto(view: ImageView, link: String?) {

    val options = RequestOptions().apply {
        optionalCircleCrop()
        placeholder(R.drawable.place_holder_dog)
        error(R.drawable.load_error_dog)
    }
    when (link){
        is String -> Glide.with(view).load(link).apply(options).into(view)
        else -> Glide.with(view).load(R.drawable.place_holder_dog).apply(options).into(view)
    }

}