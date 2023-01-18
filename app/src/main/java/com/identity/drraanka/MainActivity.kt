package com.identity.drraanka

import android.app.AlertDialog
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.identity.drraanka.databinding.ActivityMainBinding
import com.identity.drraanka.utils.ConnectivityCheck
import dagger.hilt.android.AndroidEntryPoint

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

    companion object {
        var hasInternet = false
    }
}