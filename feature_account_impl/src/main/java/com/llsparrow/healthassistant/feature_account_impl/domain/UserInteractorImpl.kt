package com.llsparrow.healthassistant.feature_account_impl.domain

import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.UserInteractor
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.core_di.qualifier.DataSource
import com.llsparrow.healthassistant.core_di.qualifier.HeapSource
import kotlinx.coroutines.withContext
import javax.inject.Inject

@FeatureScope
class UserInteractorImpl @Inject constructor(
    private val appDispatchers: AppDispatchers,
    @param:DataSource private val userDataRepository: UserRepository,
    @param:HeapSource private val heapUserRepository: UserRepository
) : UserInteractor {

    override suspend fun getUser(fromCache: Boolean): User? {
        return withContext(appDispatchers.IO) {
            var user: User? = null
            if (fromCache) {
                user = heapUserRepository.getUser(fromCache)
            }
            if (user == null) {
                user = userDataRepository.getUser(fromCache)
            }
            return@withContext user
        }
    }

    override suspend fun updateUser(userModel: User) {
        withContext(appDispatchers.IO) {
            heapUserRepository.updateUser(userModel)
            userDataRepository.updateUser(userModel)
        }
    }

    override suspend fun clear() {
        withContext(appDispatchers.IO) {
            heapUserRepository.clear()
            userDataRepository.clear()
        }
    }
}
