package com.example.demoapplication.ui.viewModels

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.demoapplication.data.entity.NoteEntity
import com.example.demoapplication.data.entity.UserEntity
import com.example.demoapplication.data.respository.NoteRepository
import com.example.demoapplication.data.respository.UserRepository
import com.example.demoapplication.ui.BaseEvent
import com.example.demoapplication.ui.base.BaseViewModel
import com.example.demoapplication.util.Output
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

class EditFragmentViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): BaseViewModel<EditFragmentViewModel.EditFragmentEvent>() {

    private val userLiveData = MutableLiveData<Output<List<UserEntity>>>()
    val _userLiveData: LiveData<Output<List<UserEntity>>> get() = userLiveData

    @ExperimentalCoroutinesApi
    override fun handleEvent(event: EditFragmentEvent) {
        when(event){

            is EditFragmentEvent.AddUser -> {

                viewModelScope.launch {
                    val insertUser: Long = userRepository.insertUser(event.userEntity)
                    Log.d("TAG", "User inserted at row: $insertUser")
                }
            }

            is EditFragmentEvent.DeleteAllUsers -> {
                viewModelScope.launch(Dispatchers.IO) {
                    userRepository.deleteAllUsers()
                }
            }

            is EditFragmentEvent.FetchUsers -> {
                viewModelScope.launch {
                    try {
                        userRepository.fetchUsers()
                            .onStart { userLiveData.value = Output.Loading }
                            .onEach {
                                userLiveData.value = Output.Success(it)
                                Log.d("LIST OF USERS", "handleEvent: "+it)
                            }
                            .launchIn(viewModelScope)
                    } catch (e: Exception) {
                        userLiveData.value = Output.Error(e)
                    }
                }
            }
        }
    }

    sealed class EditFragmentEvent : BaseEvent{

        data class AddUser(val userEntity: UserEntity) : EditFragmentEvent()

        object FetchUsers:EditFragmentEvent()

        object DeleteAllUsers:EditFragmentEvent()
    }
}


