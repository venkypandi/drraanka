package com.identity.drraanka.data.remote.model


import com.google.gson.annotations.SerializedName

data class ChitListDetails(
    @SerializedName("customer_chit_list_details")
    val customerChitListDetails: List<CustomerChitDetails?>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("page_count")
    val pageCount: Int?,
    @SerializedName("record_count")
    val recordCount: Int?
)


data class CustomerChitDetails(
    @SerializedName("chit_code")
    val chitCode: String?,
    @SerializedName("chit_emi")
    val chitEmi: String?,
    @SerializedName("chit_month")
    val chitMonth: Int?,
    @SerializedName("chit_pay_amount")
    val chitPayAmount: String?,
    @SerializedName("chit_year")
    val chitYear: String?,
    @SerializedName("payment_status")
    val paymentStatus: String?,
    @SerializedName("sl")
    val sl: Int?,
    @SerializedName("payment_date")
    val date: String?
)