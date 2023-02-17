package com.identity.drraanka

import android.app.AlertDialog
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.identity.drraanka.databinding.ActivityMainBinding
import com.identity.drraanka.utils.ConnectivityCheck
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.log

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var connectivityCheck: ConnectivityCheck? = null
    private var noInternetDialog: AlertDialog?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.btmNavigationDashboard, navHostFragment.navController)
    }

    override fun onStart() {
        super.onStart()
        createNoInternetDialog()
        connectivityCheck = ConnectivityCheck(object : ConnectivityCheck.ConnectivityCallback {
            override fun turnedOn() {
                hasInternet = true
                noInternetDialog?.dismiss()
            }

            override fun turnedOff() {
                hasInternet = false
                noInternetDialog?.show()
            }
        })
        registerReceiver(connectivityCheck, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))
    }

    private fun createNoInternetDialog() {

    }

    fun showBottomNavigationBar() {
        binding.btmNavigationDashboard.visibility = View.VISIBLE
    }
    fun hideBottomNavigationBar() {
        binding.btmNavigationDashboard.visibility = View.GONE
    }

    fun setUpActionBar(title: String, backPressedListener: () -> Unit, directionListener: () ->Unit,
                        logoutListener: () -> Unit) {
        binding.tvHeadingText.text = title
        binding.ivBack.setOnClickListener { backPressedListener() }
        binding.clActionBar.visibility = View.VISIBLE
        binding.ivProfileIcon.setOnClickListener {
            directionListener()
        }

        binding.ivLogOut.setOnClickListener {
            logoutListener()
        }
    }

    fun hideActionBar() {
        binding.clActionBar.visibility = View.GONE
    }

    companion object {
        var hasInternet = false
    }
}