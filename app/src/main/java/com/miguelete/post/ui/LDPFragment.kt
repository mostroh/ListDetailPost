package com.miguelete.post.ui

import androidx.fragment.app.Fragment
import com.miguelete.domain.Error
import com.miguelete.post.R


abstract class LDPFragment(
    contentLayout: Int
) : Fragment(contentLayout) {

    fun errorToString(error: Error) = when (error) {
        Error.Connectivity -> requireContext().getString(R.string.connectivity_error)
        is Error.Server -> requireContext().getString(R.string.server_error) + error.code
        is Error.Unknown -> requireContext().getString(R.string.unknown_error) + error.message
    }
}