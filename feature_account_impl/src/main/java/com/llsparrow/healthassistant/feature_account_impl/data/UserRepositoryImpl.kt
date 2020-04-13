package com.llsparrow.healthassistant.feature_account_impl.data

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_di.source.Local
import com.llsparrow.healthassistant.core_di.source.Remote
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_account_impl.domain.UserRepository
import javax.inject.Inject

@FeatureScope
class UserRepositoryImpl @Inject constructor(
    @param:Remote private val remoteDataSource: UserDataSource,
    @param:Local private val localDataSource: UserDataSource
) : UserRepository {
    override suspend fun getUser(fromCache: Boolean): User? {
        return if (fromCache) {
            localDataSource.getUser()
        } else {
            val user = remoteDataSource.getUser()
            user?.let { localDataSource.updateUser(it) }
            return user
        }
    }

    override suspend fun updateUser(user: User) {
        remoteDataSource.updateUser(user)
        localDataSource.updateUser(user)
    }

    override suspend fun clear() {
        localDataSource.clear()
    }
}
