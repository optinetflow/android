package com.v2ray.ang.ui.auth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.v2ray.ang.R
import com.v2ray.ang.ui.BaseActivity
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        initView()
    }

    private fun initView() {
        
    }
}