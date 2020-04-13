package com.llsparrow.healthassistant.feature_authentication_impl.di

import android.content.Context
import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.healthassistant.common_navigation_api.navigation.auth.AuthRouter
import com.llsparrow.healthassistant.feature_account_api.domain.UserInteractor
import com.llsparrow.healthassistant.feature_account_api.mapper.FirebaseUserToAppUserMapper

interface AuthenticationFeatureDependencies {
    fun context(): Context

    fun appDispatchers(): AppDispatchers

    fun userInteractor(): UserInteractor

    fun authRouter(): AuthRouter

    fun firebaseUserToAppUserMapper(): FirebaseUserToAppUserMapper
}