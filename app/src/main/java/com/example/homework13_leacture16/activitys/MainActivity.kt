package com.example.homework13_leacture16.activitys

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.homework13_leacture16.R

class MainActivity : AppCompatActivity() {

    init {
        Log.d("tag123","Activity Init")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("tag123","activityoncreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // we don't need this for now but, just let it be here, we may need it later
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController
    }

}