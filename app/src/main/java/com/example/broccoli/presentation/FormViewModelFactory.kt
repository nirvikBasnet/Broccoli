package com.example.broccoli.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.broccoli.repository.Repository

class FormViewModelFactory (private val repository: Repository,private val context: Context): ViewModelProvider.Factory  {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FormViewModel(repository,context) as T
    }

}