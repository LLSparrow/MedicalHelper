package com.llsparrow.healthassistant.feature_account_api.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class User(
    val id: String,
    val login: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val selfPhotoPath: String = "",
    val sex: Sex = Sex.MALE,
    val dateOfBirth: Date? = null,
    val email: String = ""
): Parcelable {

    fun getFullName() = "$firstName $lastName"
}