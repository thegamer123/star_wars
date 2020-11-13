package com.bouraoui.startwars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.BoringLayout
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import com.bouraoui.startwars.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as MyApplication).appComponent.movieComponent().create()
            .inject(this@MainActivity)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(view)
        setSupportActionBar(findViewById(R.id.topBarLayout))

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        val navController = navHostFragment.navController
        // val appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(this@MainActivity, navController)


        navController.addOnDestinationChangedListener { _, destination, _ ->

            when (destination.id) {
                R.id.splashScreenFragment -> {
                    binding.appbar.visibility = View.GONE
                }
                R.id.moviesListFragment -> {
                    binding.appbar.visibility = View.VISIBLE
                    supportActionBar!!.setDisplayHomeAsUpEnabled(false)
                }
                R.id.movieDetailFragment -> {
                    binding.appbar.visibility = View.VISIBLE
                    supportActionBar!!.setDisplayHomeAsUpEnabled(true)
                }
            }


        }
    }

    override fun onSupportNavigateUp() =
        findNavController(R.id.nav_host_fragment).navigateUp()

}