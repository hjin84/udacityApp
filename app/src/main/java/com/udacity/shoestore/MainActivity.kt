package com.udacity.shoestore

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentContainerView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.databinding.ActivityMainBinding
import com.udacity.shoestore.models.ShoeViewModel
import com.udacity.shoestore.models.Shoe



class MainActivity : AppCompatActivity() {

//    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var viewModel: ShoeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(ShoeViewModel::class.java)

        val binding = DataBindingUtil.setContentView <ActivityMainBinding>(this, R.layout.activity_main)
        //drawerLayout = binding.drawerLayout

        //findNavController(R.id.nav_host_fragment)
        val navController = (supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment).navController
        appBarConfiguration = AppBarConfiguration(setOf(R.id.shoelist))
        // prevent nav gesture if not on start destination
        navController.addOnDestinationChangedListener { nc: NavController, nd: NavDestination, bundle: Bundle? ->
            val showButton = showUpButton(nd.id)
            supportActionBar?.setDisplayShowHomeEnabled(showButton)
            supportActionBar?.setDisplayHomeAsUpEnabled(showButton)
        }
        NavigationUI.setupWithNavController(binding.navView, navController)
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.myNavHostFragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }
    private fun showUpButton(id: Int): Boolean {
        return id != R.id.welcome && id != R.id.instruction
                && id != R.id.shoelist && id != R.id.login
    }
    override fun onBackPressed() {
        val navController = (supportFragmentManager.findFragmentById(R.id.myNavHostFragment) as NavHostFragment).navController
        if (navController.currentDestination!!.id != R.id.shoeDetail)
            return

        super.onBackPressed()
    }
}
