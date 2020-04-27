package com.llsparrow.healthassistant.feature_medicine_list.presentation.model

import com.llsparrow.healthassistant.core_ui.list.ListItem


data class MedicineItem(
    val itemId: String? = null,
    val name: String,
    val desc: String? = null,
    val logoUri: String? = null
):ListItem()