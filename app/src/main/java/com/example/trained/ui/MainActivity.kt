package com.example.trained.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.example.trained.R
import com.example.trained.databinding.ActivityMainBinding
import com.example.trained.utils.ThemeState
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Trained)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun saveDateThemeState(state: ThemeState) {
        val sheared = getSharedPreferences("stateTheme", MODE_PRIVATE)

        sheared.edit().apply{
            putString("STATE_KEY", state.toString())
        }.apply()
    }

    fun loadDataThemeState(): String? {
        val sheared = getSharedPreferences("stateTheme", MODE_PRIVATE)

        return sheared.getString("STATE_KEY", null)
    }

    fun setTheme () {
        when(loadDataThemeState()) {
            null -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
            "SYSTEM" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                delegate.applyDayNight()
            }
            "DARK" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                delegate.applyDayNight()
            }
            "WHITE" -> {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                delegate.applyDayNight()
            }
        }

        when(getStateDisableScreen()) {
            true -> {
                window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
            false -> {
                window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            }
        }
    }

    fun saveStateDisableScreen(state: Boolean) {
        val sheared = getSharedPreferences("stateDisableScreen", MODE_PRIVATE)

        sheared.edit().apply{
            putBoolean("DISABLE_KEY", state)
        }.apply()
    }

    fun getStateDisableScreen(): Boolean {
        val sheared = getSharedPreferences("stateDisableScreen", MODE_PRIVATE)

        return sheared.getBoolean("DISABLE_KEY", false)
    }

    override fun onStart() {
        super.onStart()
        setTheme()
    }
}