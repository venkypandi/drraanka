package com.identity.drraanka.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.identity.drraanka.data.repository.MyRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(private val repository: MyRepositoryImpl):ViewModel(){

     val registrationResponse = repository.registerUser

    fun registerUser(params: Map<String, String>) {
        viewModelScope.launch {
            repository.registerUser(params)
        }
    }
}