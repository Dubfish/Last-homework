package com.dubfish.android.lasthomework

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val ID = "last chosen id"

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView
    private val bananaFragment = BananaFragment()
    private val appleFragment = AppleFragment()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(ID, bottomNavigationView.selectedItemId)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView = findViewById(R.id.bottom_navigation_view)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.banana_button -> {
                    getFragment(bananaFragment)
                    true
                }
                R.id.apple_button -> {
                    getFragment(appleFragment)
                    true
                }
                else ->
                    false
            }
        }
        bottomNavigationView.selectedItemId = savedInstanceState?.getInt(ID) ?: R.id.apple_button
    }

    private fun getFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}