package com.example.mymvvmstructure.example2.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mymvvmstructure.example2.repository.AuthRepository
import com.example.mymvvmstructure.example2.repository.BaseRepository
import com.example.mymvvmstructure.example2.ui.login.AuthViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(
    private val repository: BaseRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when{
                modelClass.isAssignableFrom(AuthViewModel::class.java)->AuthViewModel(repository as AuthRepository) as T
            else-> throw IllegalArgumentException("Viewmodel class not found")
            }

    }

}