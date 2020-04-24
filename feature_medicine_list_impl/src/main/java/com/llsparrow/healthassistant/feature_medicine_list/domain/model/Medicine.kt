package com.llsparrow.healthassistant.feature_medicine_list.domain.model

import java.util.*

data class Medicine(
    val id: Long,
    val title: String,
    val description: String? = null,
    val amount: Int = 0,
    val expirationDate: Date? = null,
    val logoUri: String? = null
)