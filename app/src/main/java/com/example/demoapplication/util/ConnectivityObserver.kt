package com.example.demoapplication.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData

object ConnectivityObserver : SingleLiveEvent<Boolean>() {

    private lateinit var networkConnectivityUtil: NetworkConnectivityUtil
    private lateinit var connectivityManager: ConnectivityManager

    fun init(context: Context){
        networkConnectivityUtil = NetworkConnectivityUtil(context)
        connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }

    private fun notifyObservers(connectionStatus:Boolean){
        postValue(connectionStatus)
    }

    fun checkForConnection(){
        value = networkConnectivityUtil.isConnected();
    }

    fun hasConnection() = networkConnectivityUtil.isConnected()

    override fun onActive() {
        registerNetworkCallBack()
        super.onActive()
    }

    private fun registerNetworkCallBack() {
        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback)
    }

    override fun onInactive() {
        removeNetworkCallBack()
        super.onInactive()
    }

    private fun removeNetworkCallBack() {
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            notifyObservers(true)
            super.onAvailable(network)
        }

        override fun onLost(network: Network) {
            notifyObservers(false)
            super.onLost(network)
        }
    }


}