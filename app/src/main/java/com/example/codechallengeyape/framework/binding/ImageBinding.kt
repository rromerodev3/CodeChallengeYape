package com.example.codechallengeyape.framework.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageUrl")
fun imageUrl(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView).load(imageUrl).apply(RequestOptions()).circleCrop().into(imageView)
}