package com.identity.drraanka.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.identity.drraanka.data.repository.MyRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(private val repository: MyRepositoryImpl) : ViewModel() {

    val appConfigValue = repository.appConfigValue
    val mySchemeValue = repository.mySchemeResponse
    val addChitResponse = repository.addChitResponse
    val chitDetailsResponse = repository.chitDetailsResponse


    fun getAppConfig(params: Map<String, String>) {
        viewModelScope.launch {
            repository.getAppConfig(params)
        }
    }
    fun getMySchemes(params: Map<String, String>) {
        viewModelScope.launch {
            repository.getMySchemes(params)
        }
    }

    fun addChit(params: Map<String, String>) {
        viewModelScope.launch {
            repository.addChit(params)
        }
    }

    fun getChitDetails(params: Map<String, String>) {
        viewModelScope.launch {
            repository.getChitDetails(params)
        }
    }

}