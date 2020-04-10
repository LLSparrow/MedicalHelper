package com.llsparrow.healthassistant.feature_medicine_list.domain.model

import java.util.*

/**
 * @author Gusev Andrei
 * @since  1.0
 */
data class Medicine(
    val itemId: String? = null,
    val name: String,
    val description: String? = null,
    val amount: Int = 0,
    val expirationDate: Date? = null,
    val logoUri: String? = null
)