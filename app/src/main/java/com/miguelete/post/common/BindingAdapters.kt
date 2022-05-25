package com.miguelete.post.common

import android.content.res.TypedArray
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.miguelete.post.R
import java.util.Random


@BindingAdapter("goneUnless")
fun goneUnless(view: View, isVisible: Boolean) {
    view.visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("randomizeImage")
fun randomizeImage(view: AspectRatioImageView, enable: Boolean) {
    if (enable) {
        val imgs: TypedArray = view.resources.obtainTypedArray(R.array.avatars)
        val rand = Random()
        val rndInt: Int = rand.nextInt(imgs.length())
        val resID = imgs.getResourceId(rndInt, 0)
        view.setImageResource(resID)
    }
}
