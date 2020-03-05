package com.luckylittlesparrow.healthassistant.feature_medicine_list.model.presentation

import com.luckylittlesparrow.healthassistant.core_ui.list.ListItem
import java.util.*

/**
 * @author Gusev Andrei
 * @since  1.0
 */
data class Medicine(
    val itemId: String? = null,
    val name: String,
    val desc: String? = null,
    val logoUri: String? = null
):ListItem()