package ru.lempo.tmdviewer.utils

import android.databinding.BindingAdapter
import android.support.v4.widget.SwipeRefreshLayout
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Author: Oksana Pokrovskaya
 * Email: op@trinitydigital.ru
 */
object BindingAdapter {

    @JvmStatic
    @BindingAdapter("visibleIf")
    fun boolVisibility(view: View, obj: Boolean) {
        view.visibility = if (obj) View.VISIBLE else View.GONE
    }

    @JvmStatic
    @BindingAdapter("imagePath")
    fun loadUri(imageView: ImageView, path: String?) {
        if (!path.isNullOrBlank())
            Glide.with(imageView.context)
//                    .load("http://image.tmdb.org/t/p/w154/" + path)
                    .load(path)
                    .centerCrop()
                    .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("refreshing")
    fun refreshing(swipeRefreshLayout: SwipeRefreshLayout, refreshing: Boolean) {
        swipeRefreshLayout.isRefreshing = refreshing
    }
}