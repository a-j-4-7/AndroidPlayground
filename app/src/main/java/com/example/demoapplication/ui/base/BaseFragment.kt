package com.example.demoapplication.ui.base

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.demoapplication.R
import com.example.demoapplication.util.ConnectivityObserver
import com.example.demoapplication.util.onClick
import com.google.android.material.button.MaterialButton
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect


abstract class BaseFragment : Fragment() {

    private var snackBar: Snackbar? = null
    private var noInternetDialog:AlertDialog? = null
    private var noInternetDialogBuilder:MaterialAlertDialogBuilder? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setConnectivityObserver()
        return inflater.inflate(this.getLayout(), container, false)
    }


    private fun setConnectivityObserver() {
        ConnectivityObserver.observe(this, Observer { isConnected ->
            Log.d("TAG", "setConnectivityObserver: $isConnected")
            if (!isConnected) {
                Toast.makeText(requireContext(), "NOT CONNECTED", Toast.LENGTH_SHORT).show()
                this.showNoInternetDialog()
            } else {
//                snackBar?.dismiss()
                Toast.makeText(requireContext(), "CONNECTED", Toast.LENGTH_SHORT).show()
                noInternetDialog?.dismiss()
            }
        })
    }

    private fun showNoInternetDialog() {
        noInternetDialogBuilder = MaterialAlertDialogBuilder(requireContext())
        val view = layoutInflater.inflate(R.layout.no_internet_dialog,null)
        val btnRetry:MaterialButton= view.findViewById(R.id.btnRetry)
        btnRetry.setOnClickListener { this.dismissNoInternetDialog() }
        noInternetDialogBuilder!!.setView(view)
        noInternetDialog = noInternetDialogBuilder!!.create();
        noInternetDialog!!.show()
    }

    override fun onResume() {
        ConnectivityObserver.checkForConnection()
        super.onResume()
    }

    override fun onStop() {
        this.dismissNoInternetDialog()
        super.onStop()
    }

    private fun dismissNoInternetDialog() {
        noInternetDialog?.let {
            if(it.isShowing)it.dismiss()
        }
    }

    abstract fun getLayout(): Int
}