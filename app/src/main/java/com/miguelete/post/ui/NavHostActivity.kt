package com.miguelete.post.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.miguelete.post.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavHostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nav_host)
    }
}
