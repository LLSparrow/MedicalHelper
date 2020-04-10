package com.llsparrow.healthassistant.feature_account_impl.domain

import android.content.Context
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_ui.utils.clearApplicationData
import com.llsparrow.healthassistant.feature_account_api.domain.UserCleanerInteractor
import com.llsparrow.healthassistant.feature_account_api.domain.UserInteractor
import com.llsparrow.healthassistant.feature_secure_storage_api.SecureStorage
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class UserCleanerInteractorImpl @Inject constructor(
    private val context: Context,
    private val secureStorage: SecureStorage,
    private val userInteractor: UserInteractor
) : UserCleanerInteractor {

    override fun clear(full: Boolean): Completable {
        return Completable.fromAction { secureStorage.clear() }
            .doOnComplete {
                if (full) context.clearApplicationData()
            }
            .andThen(Completable.defer { userInteractor.clear() })
    }
}
