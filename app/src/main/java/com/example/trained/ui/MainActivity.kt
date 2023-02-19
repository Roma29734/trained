package com.example.trained.ui

import android.os.Bundle
import com.example.trained.R
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Trained)
        setContentView(R.layout.activity_main)
    }
}