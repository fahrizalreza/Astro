package com.astro.test.rezafahrizal.extension

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.view.Window
import android.view.WindowManager

object Extension {
    // check internet connection
    fun Context.isInternetConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    true
                }
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    true
                }
                else -> false
            }
        } else {
            return true
        }
    }

    // re enable user action after loading bar disappear
    fun Window.enableScreenAction() {
        clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }

    // user can not do any action while loading bar appear
    fun Window.disableScreenAction() {
        setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
        )
    }

}