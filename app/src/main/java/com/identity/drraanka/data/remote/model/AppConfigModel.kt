package com.identity.drraanka.data.remote.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AppConfigModel(
    @SerializedName("aboutus")
    val aboutus: AboutUs?,
    @SerializedName("appointment_types_list")
    val appointmentTypesList: List<String>?,
    @SerializedName("appointment_types_list_with_interval")
    val appointmentTypesListWithInterval: List<AppointmentTypesWithInterval>?,
    @SerializedName("articles")
    val articles: Any?,
    @SerializedName("banner_images_array")
    val bannerImagesArray: List<String>?,
    @SerializedName("banner_images_array_object")
    val bannerImagesArrayObject: List<BannerImagesArrayObject>?,
    @SerializedName("battery_strip")
    val batteryStrip: Int?,
    @SerializedName("buy_back_offer_banner_list")
    val buyBackOfferBannerList: List<String>?,
    @SerializedName("complaints_list")
    val complaintsList: List<String>?,
    @SerializedName("confirm_boolean")
    val confirmBoolean: Boolean?,
    @SerializedName("confirm_payment")
    val confirmPayment: String?,
    @SerializedName("contact")
    val contact: Contact?,
    @SerializedName("dynamic_ad_banner")
    val dynamicAdBanner: List<String>?,
    @SerializedName("emi_list")
    val emiList: List<Emi>?,
    @SerializedName("error")
    val error: Boolean?,
    @SerializedName("footer")
    val footer: Footer?,
    @SerializedName("logout_time")
    val logoutTime: Int?,
    @SerializedName("postal_charge")
    val postalCharge: Int?,
    @SerializedName("product_category_list")
    val productCategoryList: List<ProductCategory>,
    @SerializedName("scheme_list")
    val schemeList: List<Scheme>?,
    @SerializedName("session_out_message")
    val sessionOutMessage: String?,
    @SerializedName("tips")
    val tips: Any?,
    @SerializedName("update_status")
    val updateStatus: Int?,
    @SerializedName("live_rates")
    val liveRates:LiveRates?
)

data class LiveRates(
    @SerializedName("gold")
    val gold:String?,
    @SerializedName("silver")
    val silver:String?
)

data class AboutUs(
    @SerializedName("Content")
    val content: String?,
    @SerializedName("heading")
    val heading: String?
)

data class AppointmentTypesWithInterval(
    @SerializedName("interval")
    val interval: Int?,
    @SerializedName("type")
    val type: String?
)

data class BannerImagesArrayObject(
    @SerializedName("banner_id")
    val bannerId: Int?,
    @SerializedName("image")
    val image: String?
)


data class Contact(
    @SerializedName("contactlist")
    val contactlist: List<ContactList>?,
    @SerializedName("google_review_link")
    val googleReviewLink: String?,
    @SerializedName("whatsapp")
    val whatsapp: String?
)

data class Contact1(
    @SerializedName("address1")
    val address1: String?,
    @SerializedName("address2")
    val address2: String?,
    @SerializedName("area")
    val area: String?,
    @SerializedName("city")
    val city: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("landline")
    val landline: String?,
    @SerializedName("mobile")
    val mobile: String?,
    @SerializedName("pincode")
    val pincode: String?,
    @SerializedName("state")
    val state: String?
)

data class ContactList(
    @SerializedName("contact1")
    val contact1: Contact1?,
    @SerializedName("contact2")
    val contact2: Contact1?
)

data class Emi(
    @SerializedName("emi_desc_list")
    val emiDescList: List<EmiDesc?>?,
    @SerializedName("emi_icon")
    val emiIcon: String?,
    @SerializedName("emi_label_name")
    val emiLabelName: String?
)

data class EmiDesc(
    @SerializedName("desc")
    val desc: String?
)

data class Footer(
    @SerializedName("content")
    val content: String?,
    @SerializedName("facebook")
    val facebook: String?,
    @SerializedName("footer_heading")
    val footerHeading: String?,
    @SerializedName("instagram")
    val instagram: String?,
    @SerializedName("rights")
    val rights: String?
)

data class Scheme(
    @SerializedName("branch_Id")
    val branchId: String?,
    @SerializedName("chit_Code")
    val chitCode: String?,
    @SerializedName("minimum_Contribution")
    val minimumContribution: String?,
    @SerializedName("product_Value")
    val productValue: String?,
    @SerializedName("raanka_Contribution")
    val raankaContribution: String?,
    @SerializedName("scheme_Hint")
    val schemeHint: String?,
    @SerializedName("scheme_Name")
    val schemeName: String?,
    @SerializedName("tenture")
    val tenture: String?
)

data class ProductCategory(
    @SerializedName("category_id")
    val categoryId: String?,
    @SerializedName("category_name")
    val categoryName: String?,
    @SerializedName("product_list")
    val productList: List<Product?>?
)

@Parcelize
data class Product(
    @SerializedName("product_amount")
    val productAmount: String?,
    @SerializedName("product_catg_id")
    val productCatgId: String?,
    @SerializedName("product_code")
    val productCode: String?,
    @SerializedName("product_desc_1")
    val productDesc1: String?,
    @SerializedName("product_desc_2")
    val productDesc2: String?,
    @SerializedName("product_desc_3")
    val productDesc3: String?,
    @SerializedName("product_desc_4")
    val productDesc4: String?,
    @SerializedName("product_desc_5")
    val productDesc5: String?,
    @SerializedName("product_id")
    val productId: String?,
    @SerializedName("product_images")
    val productImages: ProductImages?,
    @SerializedName("product_metal")
    val productMetal: String?,
    @SerializedName("product_name")
    val productName: String?,
    @SerializedName("product_weight")
    val productWeight: String?
) : Parcelable

@Parcelize
data class ProductImages(
    @SerializedName("attachment_list")
    val attachmentList: List<Attachment?>?
):Parcelable

@Parcelize
data class Attachment(
    @SerializedName("attachment_id")
    val attachmentId: Int?,
    @SerializedName("image")
    val image: String?
):Parcelable



