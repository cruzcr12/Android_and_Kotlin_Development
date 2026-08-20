package com.echcoding.myfirebasechatbot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echcoding.myfirebasechatbot.data.Result
import com.echcoding.myfirebasechatbot.data.Room
import com.echcoding.myfirebasechatbot.data.RoomRepository
import kotlinx.coroutines.launch

class RoomViewModel: ViewModel() {
    // LiveData state for updating the list of rooms
    private val _rooms = MutableLiveData<List<Room>>()
    val rooms: LiveData<List<Room>> get() = _rooms

    private val roomRepository: RoomRepository
    init {
        roomRepository = RoomRepository(AuthViewModel.Injection.instance())
        loadRooms()
    }

    fun createRoom(name: String){
        viewModelScope.launch {
            roomRepository.createRoom(name)
        }
    }

    // Load the rooms from the repository
    fun loadRooms(){
        viewModelScope.launch {
            when(val result = roomRepository.getRooms()){
                is Result.Success -> _rooms.value = result.data
                is Result.Error -> {
                    // Handle error
                }
            }
        }
    }

}