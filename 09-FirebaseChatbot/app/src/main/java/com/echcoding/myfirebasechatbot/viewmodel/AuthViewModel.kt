package com.echcoding.myfirebasechatbot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.echcoding.myfirebasechatbot.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import com.echcoding.myfirebasechatbot.data.Result

class AuthViewModel: ViewModel() {
    private val userRepository: UserRepository

    // To initialize the repository we need to pass in the auth and firestore
    // The auth instance can be called directly within the class
    // For the firestore object we need to create an object class to make it accessible
    object Injection {
        private val instance: FirebaseFirestore by lazy {
            FirebaseFirestore.getInstance()
        }
        fun instance(): FirebaseFirestore {
            return instance
        }
    }

    // Create the instance of the repository
    init {
        userRepository = UserRepository(
            FirebaseAuth.getInstance(),
            Injection.instance())
    }

    // Create an authResult holder for both signup and login
    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun signUp(email: String, password: String, firstName: String, lastName: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.signUp(email, password, firstName, lastName)
        }
    }

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _authResult.value = userRepository.login(email, password)
        }
    }


}