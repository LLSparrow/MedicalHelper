package com.llsparrow.healthassistant.feature_account_api.domain.model

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User  constructor(
    val id: String? = null,
    val login: String = "",
    val firstName: String? = "",
    val lastName: String? = "",
    val selfPhotoPath: Uri? = null,
    val sex: Sex = Sex.MALE,
    val dateOfBirth: Date? = null,
    val email: String? = "",
    val fullname: String? = ""
) : Parcelable {

    fun getFullName() = "$firstName $lastName"
}