package com.miguelete.post.common

import android.content.res.TypedArray
import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miguelete.post.R
import com.miguelete.post.ui.main.PostAdapter
import com.miguelete.post.ui.main.PostItemUiState
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
@BindingAdapter("items")
fun RecyclerView.setItems(posts: List<PostItemUiState>?) {
    if (posts != null) {
        (adapter as? PostAdapter)?.submitList(posts)
    }
}
