package com.example.demoapplication.ui.base

import androidx.lifecycle.ViewModel


abstract class BaseViewModel<T : BaseEvent> :ViewModel() {

//    protected val baseCoroutinesExceptionHandler = CoroutineExceptionHandler{
//            _, exception ->
//        exception.message?.let { catchCoroutinesException(it) }
//    }

    abstract fun handleEvent(event: T)

//    abstract fun catchCoroutinesException(errorMsg:String)
}
