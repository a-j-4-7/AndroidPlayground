package com.example.demoapplication.ui.viewModels

import android.util.Log
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.demoapplication.data.entity.NoteEntity
import com.example.demoapplication.data.respository.NoteRepository
import com.example.demoapplication.ui.BaseEvent
import com.example.demoapplication.ui.base.BaseViewModel
import com.example.demoapplication.util.Output
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class HomeFragmentViewModel @ViewModelInject constructor(
    private val noteRepository: NoteRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): BaseViewModel<HomeFragmentViewModel.HomeEvent>() {

    private val notesLiveData = MutableLiveData<Output<List<NoteEntity>>>()
    val _notesLiveData: LiveData<Output<List<NoteEntity>>> get() = notesLiveData

    override fun handleEvent(event: HomeEvent) {
        when(event){
            HomeEvent.onNothing -> {

            }

            HomeEvent.fetchNotes -> {
                viewModelScope.launch {
                    noteRepository.fetchNotes()
                        .onEach {
                            notesLiveData.value = it
                        }
                        .launchIn(viewModelScope)
                }
            }
        }
    }

    sealed class HomeEvent : BaseEvent{

        object onNothing : HomeEvent()

        object fetchNotes : HomeEvent()
    }
}


