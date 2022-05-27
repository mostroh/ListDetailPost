package com.miguelete.post.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineScope
import com.miguelete.domain.Error
import com.miguelete.post.R

fun Fragment.buildMainState(
    context: Context = requireContext(),
    scope: CoroutineScope = viewLifecycleOwner.lifecycleScope,
    navController: NavController = findNavController()
) = MainState(context, scope, navController)

class MainState(
    private val context: Context,
    private val scope: CoroutineScope,
    private val navController: NavController
) {
    fun onPostClicked(postItemUiState: PostItemUiState) {
        val action = MainFragmentDirections.actionMainToDetail(postItemUiState.id)
        navController.navigate(action)
    }

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> context.getString(R.string.connectivity_error)
        is Error.Server -> context.getString(R.string.server_error) + error.code
        is Error.Unknown -> context.getString(R.string.unknown_error) + error.message
    }

}
