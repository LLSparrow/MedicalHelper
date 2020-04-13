package com.llsparrow.healthassistant.feature_authentication_impl.domain

import android.content.Intent
import com.llsparrow.core_base_api.domain.interactor.ResultInteractor
import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.UserInteractor
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@FeatureScope
class GoogleInteractor @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatchers: AppDispatchers,
    private val userInteractor: UserInteractor
) : ResultInteractor<GoogleInteractor.Params, User?>() {

    override val dispatcher: CoroutineDispatcher
        get() = dispatchers.IO

    override suspend fun doWork(params: Params): User? {
        val user = authRepository.signInWithGoogle(params.data)
        user?.let { userInteractor.updateUser(user) }
        return user
    }

    data class Params(val data: Intent)
}