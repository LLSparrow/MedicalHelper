package com.llsparrow.healthassistant.feature_personal_info_api.validator

import java.util.regex.Pattern

class EmailValidator {
    companion object {
        @JvmStatic
        private val pattern =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

        @JvmStatic
        fun isValid(emailStr: String): Boolean {
            val matcher = pattern.matcher(emailStr)
            return matcher.find()
        }
    }
}