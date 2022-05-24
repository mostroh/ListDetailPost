package com.miguelete.post.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miguelete.domain.Post
import com.miguelete.post.databinding.ViewPostItemBinding

class PostAdapter(private val onPostClick:(Post) -> Unit):
    ListAdapter<Post, PostAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Post, newItem: Post) =
            oldItem == newItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ViewPostItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onPostClick
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ViewPostItemBinding,
        private val onPostClick: (Post) -> Unit,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post) = with(binding) {
            item = post
            binding.root.setOnClickListener { onPostClick(post) }
            binding.executePendingBindings()
        }
    }
}
