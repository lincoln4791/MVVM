package com.example.mymvvmstructure.example1.ViewModel

import androidx.lifecycle.ViewModel
import com.example.mymvvmstructure.example1.SearchRepository

class PostsViewModel : ViewModel() {
    suspend fun postsSearch(repository: SearchRepository) = repository.readData()
}