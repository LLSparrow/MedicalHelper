package com.llsparrow.healthassistant.feature_account_impl.domain

import com.llsparrow.healthassistant.feature_account_api.domain.model.User

interface UserRepository {

    suspend fun getUser(fromCache: Boolean): User?

    suspend fun updateUser(user: User)

    suspend fun clear()
}
