package com.example.demoapplication.util

import java.lang.Exception

sealed class Output<out T> {

    object Loading : Output<Nothing>()

    data class Error(val exception: Exception):Output<Nothing>()

    data class Success<T>(val data : T): Output<T>()
}