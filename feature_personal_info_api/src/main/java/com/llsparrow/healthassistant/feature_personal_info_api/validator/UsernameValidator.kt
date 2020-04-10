package com.llsparrow.healthassistant.feature_personal_info_api.validator

import java.util.regex.Pattern

class UsernameValidator {
    companion object {
        @JvmStatic
        private val pattern =
                Pattern.compile("[a-z_0-9]{1,32}", Pattern.CASE_INSENSITIVE)

        @JvmStatic
        fun isValid(username: String): Boolean {
            val matcher = pattern.matcher(username)
            return matcher.matches()
        }
    }
}