package com.identity.drraanka.data.remote.model


import com.google.gson.annotations.SerializedName

data class CustomerChitListModel(
    @SerializedName("customer_chit_list")
    val customerChitList: List<CustomerChit?>?,
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


data class CustomerChit(
    @SerializedName("chit_code")
    val chitCode:String?,
    @SerializedName("chit_end_date ")
    val chitEndDate: String?,
    @SerializedName("chit_name")
    val chitName: String?,
    @SerializedName("chit_start_date")
    val chitStartDate: String?,
    @SerializedName("chit_status")
    val chitStatus: String?,
    @SerializedName("total_amount")
    val totalAmount: Int?,
    @SerializedName("branch_id")
    val branchId: String?,
    @SerializedName("chit_tenure")
    val tenure: String?,
    @SerializedName("pending_months")
    val pending:String?,
    @SerializedName("contribution")
    val contribution:String? = null
)