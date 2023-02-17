package com.identity.drraanka.data.remote.api

import com.identity.drraanka.data.remote.model.*
import retrofit2.Response
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RaankaApiService {

    @POST("getOtp.php")
    @FormUrlEncoded
    suspend fun getOtp(@FieldMap params: Map<String, String>): Response<LoginResponseModel>

    @POST("validateOtp.php")
    @FormUrlEncoded
    suspend fun validateOtp(@FieldMap params: Map<String, String>): Response<OtpResponseModel>

    @POST("appConfig.php")
    @FormUrlEncoded
    suspend fun getAppConfig(@FieldMap params: Map<String, String>): Response<AppConfigModel>

    @POST("user_Registration.php")
    @FormUrlEncoded
    suspend fun registerUser(@FieldMap params: Map<String, String>): Response<RegistrationResponse>

    @POST("get_Customer_Chit_Scheme.php")
    @FormUrlEncoded
    suspend fun getMySchemes(@FieldMap params: Map<String, String>): Response<CustomerChitListModel>

    @POST("add_chit_scheme.php")
    @FormUrlEncoded
    suspend fun addChit(@FieldMap params: Map<String, String>): Response<OtpResponseModel>

    @POST("get_Customer_Chit_Details.php")
    @FormUrlEncoded
    suspend fun getChitDetails(@FieldMap params: Map<String, String>): Response<ChitListDetails>


}