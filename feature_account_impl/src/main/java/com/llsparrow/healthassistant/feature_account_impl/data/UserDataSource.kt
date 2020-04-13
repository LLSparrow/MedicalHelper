package com.llsparrow.healthassistant.feature_account_impl.data

import com.llsparrow.healthassistant.feature_account_api.domain.model.User

interface UserDataSource {

    suspend fun getUser(): User?

    suspend fun updateUser(user: User)

    suspend fun clear()
}
