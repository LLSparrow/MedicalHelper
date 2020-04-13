package com.llsparrow.healthassistant.feature_authentication_impl.domain

import android.content.Intent
import com.llsparrow.core_base_api.domain.interactor.ResultInteractor
import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_authentication_api.domain.CheckLoginInteractor
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

@FeatureScope
class CheckLoginInteractorImpl @Inject constructor(
    private val authRepository: AuthRepository,
    private val dispatchers: AppDispatchers
) : ResultInteractor<Unit, User?>(), CheckLoginInteractor {

    override val dispatcher: CoroutineDispatcher
        get() = dispatchers.IO

    override suspend fun checkLogin(): User? {
        return doWork(Unit)
    }

    override suspend fun doWork(params: Unit): User? {
        return authRepository.checkLogin()
    }

    data class Params(val data: Intent)
}