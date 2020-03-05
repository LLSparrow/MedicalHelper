package com.luckylittlesparrow.healthassistant.feature_account_api.model

import java.util.*

/**
 * @author Gusev Andrei
 * @since  1.0
 */
data class User(
    val id: String,
    val login: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val selfPhotoPath: String = "",
    val sex: Sex = Sex.MALE,
    val dateOfBirth: Date? = null,
    val email: String = ""
)