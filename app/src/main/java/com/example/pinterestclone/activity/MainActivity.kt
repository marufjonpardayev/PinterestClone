package com.example.pinterestclone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.pinterestclone.R
import com.example.pinterestclone.fragments.ChatFragment
import com.example.pinterestclone.fragments.HomeFragment
import com.example.pinterestclone.fragments.ProfileFragment
import com.example.pinterestclone.fragments.SearchFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val homeFragment=HomeFragment()
        val searchFragment=SearchFragment()
        val chatFragment=ChatFragment()
        val profileFragment=ProfileFragment()
        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        setCurrentFragment(homeFragment)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> setCurrentFragment(homeFragment)
                R.id.ic_search -> setCurrentFragment(searchFragment)
                R.id.ic_chat -> setCurrentFragment(chatFragment)
                R.id.ic_person -> setCurrentFragment(profileFragment)

            }
            true
        }
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_Fragment, fragment)
            commit()
        }
    }
}