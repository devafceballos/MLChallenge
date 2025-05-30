package com.example.mlchallenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.mlchallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as? NavHostFragment

        setSupportActionBar(binding.myToolbar)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.searchFragment))
        navHost?.findNavController()
            ?.let {
                navController = it
                setupActionBarWithNavController(it, appBarConfiguration) }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
