package com.llsparrow.healthassistant.feature_authentication_api.domain

import com.llsparrow.healthassistant.feature_account_api.domain.model.User

interface CheckLoginInteractor {
    suspend fun checkLogin(): User?
}