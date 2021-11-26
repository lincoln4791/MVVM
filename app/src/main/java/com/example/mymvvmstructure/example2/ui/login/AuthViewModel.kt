package com.example.mymvvmstructure.example2.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mymvvmstructure.example1.Resource
import com.example.mymvvmstructure.example2.dataClass.LoginResponse
import com.example.mymvvmstructure.example2.repository.AuthRepository
import com.example.mymvvmstructure.example2.resource.SealedResources
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {

    private val _loginResponse:MutableLiveData<SealedResources<LoginResponse>> = MutableLiveData()
    val loginResponse : LiveData<SealedResources<LoginResponse>>
            get() = _loginResponse



    fun login(email:String,pass:String) = viewModelScope.launch {
        _loginResponse.value = repository.login(email,pass)
    }

}