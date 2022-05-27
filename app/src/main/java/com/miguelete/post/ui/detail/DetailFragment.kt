package com.miguelete.post.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.miguelete.post.R
import com.miguelete.post.common.launchAndCollect
import com.miguelete.post.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        binding.toolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }

        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if (state.post != null) {
                binding.post = state.post
            }
        }
    }
}