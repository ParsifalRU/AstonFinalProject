package com.example.astonfinalproject.main.features.profile.presentation

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.astonfinalproject.main.features.profile.presentation.character.CharacterFragment
import com.example.astonfinalproject.main.features.profile.presentation.episode.EpisodeFragment
import com.example.astonfinalproject.main.features.profile.presentation.location.LocationFragment
import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {

    private var isSupportFragmentManagerEnabled = false

    override fun onCreate(savedInstanceState: Bundle?) {

        setSplashScreen()
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null){
            isSupportFragmentManagerEnabled = savedInstanceState.getBoolean(FRAGMENT_MANAGER_KEY)
        }
        setContentView(com.example.astonfinalproject.R.layout.activity_main)
        if (!isSupportFragmentManagerEnabled){
            setFragment(CharacterFragment())
            isSupportFragmentManagerEnabled = true
        }
        setBottomNavigation()

        supportActionBar?.setHomeAsUpIndicator(null)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
    }

    private fun setSplashScreen(){
        installSplashScreen()
        Thread.sleep(2000)
    }

    private fun setFragment(fragment : Fragment){
        supportFragmentManager
            .beginTransaction()
            .replace(com.example.astonfinalproject.R.id.mainFragmentContainer, fragment)
            .commit()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(FRAGMENT_MANAGER_KEY, isSupportFragmentManagerEnabled)
    }

    private fun setBottomNavigation(){
        val bottomNavigation = findViewById<BottomNavigationView>(com.example.astonfinalproject.R.id.bottomNavigationView)
        bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                com.example.astonfinalproject.R.id.navigation_character -> {
                    setFragment(CharacterFragment())
                    return@setOnItemSelectedListener true
                }
                com.example.astonfinalproject.R.id.navigation_episode -> {
                    setFragment(EpisodeFragment())
                    return@setOnItemSelectedListener true
                }
                com.example.astonfinalproject.R.id.navigation_location -> {
                    setFragment(LocationFragment())
                    return@setOnItemSelectedListener true
                }
                else -> {return@setOnItemSelectedListener false}
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val FRAGMENT_MANAGER_KEY = "FRAGMENT_MANAGER_KEY"
    }
}