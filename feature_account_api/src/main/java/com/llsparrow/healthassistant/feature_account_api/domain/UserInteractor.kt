package com.llsparrow.healthassistant.feature_account_api.domain

import com.llsparrow.healthassistant.feature_account_api.domain.model.User

interface UserInteractor {

    suspend fun getUser(fromCache: Boolean): User?

    suspend fun updateUser(userModel: User)

    suspend fun clear()
}
