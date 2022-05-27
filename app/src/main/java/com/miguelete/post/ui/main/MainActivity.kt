package com.miguelete.post.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.miguelete.post.databinding.ActivityMainBinding
import com.miguelete.post.ui.main.MainViewModel.MainUiState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels()

    private val postAdapter by lazy { PostAdapter(viewModel::onPostClick) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.recycler.adapter = postAdapter
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect(::updateUi)
            }
        }
    }

    private fun updateUi(uiState: MainUiState) {
        uiState.posts?.let(postAdapter::submitList)
    }

    private fun navigateTo(postId: String) {
        //TODO
    }
}
