package com.example.mymvvmstructure.example2.ui.login

import androidx.lifecycle.ViewModel
import com.example.mymvvmstructure.example2.repository.AuthRepository

class AuthViewModel(
    private val repository: AuthRepository
) : ViewModel() {
}