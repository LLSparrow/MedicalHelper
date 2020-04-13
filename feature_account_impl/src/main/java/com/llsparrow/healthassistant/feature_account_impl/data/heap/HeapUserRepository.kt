package com.llsparrow.healthassistant.feature_account_impl.data.heap

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_account_impl.domain.UserRepository
import javax.inject.Inject

@FeatureScope
class HeapUserRepository @Inject constructor() : UserRepository {

    private var userModel: User? = null

    override suspend fun getUser(fromCache: Boolean): User? {
        return userModel
    }

    override suspend fun updateUser(user: User) {
        this.userModel = user
    }

    override suspend fun clear() {
        userModel = null
    }
}
