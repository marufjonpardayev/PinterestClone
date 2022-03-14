package com.example.pinterestclone.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pinterestclone.R
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewParent
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.example.pinterestclone.adapter.PagerAdapter
import com.example.pinterestclone.fragments.DetailsFragment
import com.example.pinterestclone.model.WelcomeElement
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DetailsPhotoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_photo)
        initViews()
    }
    private fun initViews() {
        val vpDetails = findViewById<ViewPager>(R.id.vp_details)
        refreshAdapter(vpDetails, getList(), getPosition())
    }
    private fun refreshAdapter(viewPager: ViewPager, photoList: ArrayList<WelcomeElement>, position: Int) {
        val adapter = PagerAdapter(supportFragmentManager)
        for (photoItem in photoList) {
            adapter.addFragment(DetailsFragment(photoItem))
        }
        viewPager.adapter = adapter
        viewPager.currentItem = position
    }
    private fun getList(): ArrayList<WelcomeElement> {
        val json = intent.getStringExtra("photoList")
        val type: Type = object : TypeToken<ArrayList<WelcomeElement>>() {}.type
        return Gson().fromJson(json, type)
    }

    private fun getPosition(): Int {
        return intent.getIntExtra("position", 0)
    }

    private fun setStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val decor = this.window.decorView
            decor.systemUiVisibility = 0
        }
        window.statusBarColor = Color.BLACK
    }
}