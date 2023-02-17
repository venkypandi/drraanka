package com.identity.drraanka.data.repository

interface MyRepository {

    suspend fun getOtp(params: Map<String, String>)

    suspend fun validateOtp(params: Map<String, String>)

    suspend fun getAppConfig(params: Map<String, String>)

    suspend fun registerUser(params: Map<String, String>)

    suspend fun getMySchemes(params: Map<String, String>)

    suspend fun addChit(params: Map<String, String>)

    suspend fun getChitDetails(params: Map<String, String>)



}