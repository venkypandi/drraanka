package com.identity.drraanka.data.remote.model


import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @SerializedName("customerId")
    val customerId: Any?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("LastId")
    val lastId: Any?,
    @SerializedName("message")
    val message: String?
)