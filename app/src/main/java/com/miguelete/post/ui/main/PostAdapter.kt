package com.miguelete.post.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.miguelete.post.databinding.ViewPostItemBinding

class PostAdapter(private val onPostClick:(PostItemUiState) -> Unit):
    ListAdapter<PostItemUiState, PostAdapter.ViewHolder>(DiffCallback()) {

    class DiffCallback : DiffUtil.ItemCallback<PostItemUiState>() {
        override fun areItemsTheSame(oldItem: PostItemUiState, newItem: PostItemUiState) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: PostItemUiState, newItem: PostItemUiState) =
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
        private val onPostClick: (PostItemUiState) -> Unit,
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(post: PostItemUiState) = with(binding) {
            item = post
            binding.root.setOnClickListener { onPostClick(post) }
            binding.executePendingBindings()
        }
    }
}
