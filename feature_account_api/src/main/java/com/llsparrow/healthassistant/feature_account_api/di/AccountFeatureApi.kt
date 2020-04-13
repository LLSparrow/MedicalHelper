package com.llsparrow.healthassistant.feature_account_api.di

import com.llsparrow.healthassistant.feature_account_api.domain.UserCleanerInteractor
import com.llsparrow.healthassistant.feature_account_api.domain.UserInteractor

interface AccountFeatureApi {
     fun userCleanerInteractor(): UserCleanerInteractor
     fun userInteractor(): UserInteractor
}