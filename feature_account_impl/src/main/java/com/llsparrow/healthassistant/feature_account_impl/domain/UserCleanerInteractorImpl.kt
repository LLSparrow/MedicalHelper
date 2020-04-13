package com.llsparrow.healthassistant.feature_account_impl.domain

import android.content.Context
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_ui.utils.clearApplicationData
import com.llsparrow.healthassistant.feature_account_api.domain.UserCleanerInteractor
import com.llsparrow.healthassistant.feature_account_api.domain.UserInteractor
import com.llsparrow.healthassistant.feature_secure_storage_api.SecureStorage
import javax.inject.Inject

@FeatureScope
class UserCleanerInteractorImpl @Inject constructor(
    private val context: Context,
    private val secureStorage: SecureStorage,
    private val userInteractor: UserInteractor
) : UserCleanerInteractor {

    override suspend fun clear(full: Boolean) {
        secureStorage.clear()

        if (full) context.clearApplicationData()

        userInteractor.clear()
    }
}
