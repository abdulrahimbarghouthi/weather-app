package com.barghouthi.myapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.barghouthi.myapp.databinding.ActivityMainBinding
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.barghouthi.myapp.R


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)



    }

    override fun onStart() {
        super.onStart()
        val appBarConfig = AppBarConfiguration(findNavController(R.id.fragmentContainerView2).graph)
        setupActionBarWithNavController(findNavController(R.id.fragmentContainerView2),appBarConfig)
    }

    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.fragmentContainerView2)
        return navController.navigateUp() || super.onSupportNavigateUp()

    }








}