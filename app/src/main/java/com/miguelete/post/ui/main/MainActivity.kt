package com.miguelete.post.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.miguelete.post.databinding.ActivityMainBinding
import com.miguelete.post.ui.main.MainViewModel.MainUiState
import dagger.hilt.android.AndroidEntryPoint

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
    }

    private fun updateUi(uiState: MainUiState) {
        // TODO update ui
    }
}
