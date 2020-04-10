package com.llsparrow.healthassistant.feature_account_api.domain

import io.reactivex.Completable

interface UserCleanerInteractor {

    fun clear(full: Boolean = false): Completable
}
