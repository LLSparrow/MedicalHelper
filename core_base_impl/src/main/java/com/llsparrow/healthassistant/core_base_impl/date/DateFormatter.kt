package com.llsparrow.healthassistant.core_base_impl.date

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter private constructor() {

    companion object {
        const val YYYY_DD_MM_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"
        const val DD_MM_YYYY = "dd.MM.yyyy"
        const val DD_MMMM = "dd MMMM"
        private const val EMPTY = ""

        @JvmStatic
        fun format(date: Date?, dateFormat: String): String {
            if (date == null) return EMPTY
            val format = SimpleDateFormat(dateFormat, Locale.getDefault())
            return format.format(date)
        }
    }
}
