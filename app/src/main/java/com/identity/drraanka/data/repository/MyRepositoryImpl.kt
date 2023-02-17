package com.identity.drraanka.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.identity.drraanka.data.remote.api.RaankaApiService
import com.identity.drraanka.data.remote.model.*
import com.identity.drraanka.utils.Resource
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(private val apiService: RaankaApiService) : MyRepository {

    private val _otpResponseValue = MutableLiveData<Resource<LoginResponseModel>>()
    val otpResponseValue: LiveData<Resource<LoginResponseModel>>
        get() = _otpResponseValue

    private val _otpValidateValue = MutableLiveData<Resource<OtpResponseModel>>()
    val otpValidateValue: LiveData<Resource<OtpResponseModel>>
        get() = _otpValidateValue

    private val _appConfigValue = MutableLiveData<Resource<AppConfigModel>>()
    val appConfigValue: LiveData<Resource<AppConfigModel>>
        get() = _appConfigValue

    private val _registerUser = MutableLiveData<Resource<RegistrationResponse>>()
    val registerUser: LiveData<Resource<RegistrationResponse>>
        get() = _registerUser

    private val _mySchemeResponse = MutableLiveData<Resource<CustomerChitListModel>>()
    val mySchemeResponse: LiveData<Resource<CustomerChitListModel>>
        get() = _mySchemeResponse

    private val _addChitResponse = MutableLiveData<Resource<OtpResponseModel>>()
    val addChitResponse: LiveData<Resource<OtpResponseModel>>
        get() = _addChitResponse

    private val _chitDetailsResponse = MutableLiveData<Resource<ChitListDetails>>()
    val chitDetailsResponse: LiveData<Resource<ChitListDetails>>
        get() = _chitDetailsResponse

    override suspend fun getOtp(params: Map<String, String>) {
        _otpResponseValue.value = Resource.loading(null)
        val response = apiService.getOtp(params)
        if (response.isSuccessful) {

            _otpResponseValue.value = Resource.success(response.body())
        } else {
            _otpResponseValue.value = Resource.error("Something went wrong!", response.code(), null)
        }
    }

    override suspend fun validateOtp(params: Map<String, String>) {
        _otpValidateValue.value = Resource.loading(null)
        val response = apiService.validateOtp(params)
        if (response.isSuccessful) {

            _otpValidateValue.value = Resource.success(response.body())
        } else {
            _otpValidateValue.value = Resource.error("Something went wrong!", response.code(), null)
        }
    }

    override suspend fun getAppConfig(params: Map<String, String>) {
        _appConfigValue.value = Resource.loading(null)
        val response = apiService.getAppConfig(params)
        if (response.isSuccessful) {

            _appConfigValue.value = Resource.success(response.body())
        } else {
            _appConfigValue.value = Resource.error("Something went wrong!", response.code(), null)

        }
    }

    override suspend fun registerUser(params: Map<String, String>) {
        _registerUser.value = Resource.loading(null)
        val response = apiService.registerUser(params)
        if (response.isSuccessful) {

            _registerUser.value = Resource.success(response.body())
        } else {
            _registerUser.value = Resource.error("Something went wrong!", response.code(), null)

        }
    }

    override suspend fun getMySchemes(params: Map<String, String>) {
        _mySchemeResponse.value = Resource.loading(null)
        val response = apiService.getMySchemes(params)
        if (response.isSuccessful) {

            _mySchemeResponse.value = Resource.success(response.body())
        } else {
            _mySchemeResponse.value = Resource.error("Something went wrong!", response.code(), null)

        }
    }

    override suspend fun addChit(params: Map<String, String>) {
        _addChitResponse.value = Resource.loading(null)
        val response = apiService.addChit(params)
        if (response.isSuccessful) {
            _addChitResponse.value = Resource.success(response.body())
        } else {
            _addChitResponse.value = Resource.error("Something went wrong!", response.code(), null)

        }
    }

    override suspend fun getChitDetails(params: Map<String, String>) {
        _chitDetailsResponse.value = Resource.loading(null)
        val response = apiService.getChitDetails(params)
        if (response.isSuccessful) {
            _chitDetailsResponse.value = Resource.success(response.body())
        } else {
            _chitDetailsResponse.value = Resource.error("Something went wrong!", response.code(), null)

        }
    }

}