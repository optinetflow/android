package com.v2ray.ang.ui.auth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.v2ray.ang.ui.compose.OptiNetApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            OptiNetApp()
        }
    }
}