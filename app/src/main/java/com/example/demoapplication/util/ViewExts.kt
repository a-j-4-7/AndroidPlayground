package com.example.demoapplication.util

import android.view.View
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
fun View.onClick():Flow<View> {
    return callbackFlow {
        setOnClickListener {
            offer(it)
        }
        awaitClose { setOnClickListener(null) }
    }
}
