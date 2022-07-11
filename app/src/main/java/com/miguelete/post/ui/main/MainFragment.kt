package com.miguelete.post.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.miguelete.post.R
import com.miguelete.post.common.launchAndCollect
import com.miguelete.post.databinding.FragmentMainBinding
import com.miguelete.post.ui.LDPFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : LDPFragment(R.layout.fragment_main) {

    private val viewModel: MainViewModel by viewModels()

    private val navController: NavController by lazy { findNavController() }

    private val adapter = PostAdapter(::onPostClicked)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentMainBinding.bind(view).apply {
            recycler.adapter = adapter
        }

        viewLifecycleOwner.launchAndCollect(viewModel.state) {
            binding.loading = it.loading
            binding.posts = it.posts
            binding.error = it.error?.let(::errorToString)
        }

        viewModel.onUiReady()
    }

    private fun onPostClicked(postItemUiState: PostItemUiState) {
        val action = MainFragmentDirections.actionMainToDetail(postItemUiState.id)
        navController.navigate(action)
    }

}
