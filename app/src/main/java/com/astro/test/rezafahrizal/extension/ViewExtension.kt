package com.astro.test.rezafahrizal.extension

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.astro.test.rezafahrizal.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import java.util.*
import kotlin.concurrent.schedule

object ViewExtension {

    fun EditText.afterTextChanged(listener: (p0: CharSequence?) -> Unit) {

        this.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                Timer().schedule(300) {
                    listener.invoke(p0)
                }
            }

        })
    }

    fun ImageView.loadImage(@DrawableRes resource: Int) {
        try {
            this.setImageResource(resource)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    fun ImageView.loadImage(
        url: String?,
        radius: Int? = 0
    ) {
        if (url.isNullOrEmpty()) return
        loadImage(url, null, null, radius)
    }

    @SuppressLint("CheckResult")
    fun ImageView.loadImage(
        url: String?,
        @DrawableRes placeholder: Int?,
        @DrawableRes errorDrawable: Int?,
        radius: Int? = 0,
        downSampling: Boolean = false
    ) {
        if (url.isNullOrEmpty()) return
        val options = RequestOptions().skipMemoryCache(true)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        if (radius != null && radius > 0) options.transform(RoundedCorners(radius))
        if (placeholder != null) {
            try {
                val placeholderD = ContextCompat.getDrawable(this.context, placeholder)
                options.placeholder(placeholderD)
            } catch (e: Exception) {
            }
        }
        if (errorDrawable != null) {
            try {
                val errorD = ContextCompat.getDrawable(this.context, errorDrawable)
                options.error(errorD)
            } catch (e: Exception) {
            }
        }
        try {
            val builder = Glide.with(this)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(400))
            if (downSampling) {
                builder.thumbnail(0.25f)
            }
            builder.apply(options)
                .into(this)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    fun ImageView.loadCircleImage(url: String?) {
        if (url.isNullOrEmpty()) return
        try {
            Glide.with(this)
                .load(url)
                .transition(DrawableTransitionOptions.withCrossFade(400))
                .apply(
                    RequestOptions().skipMemoryCache(true)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .placeholder(R.drawable.ic_document)
                        .circleCrop()
                )
                .into(this)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }


}