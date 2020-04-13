package com.llsparrow.healthassistant.feature_account_api.domain

interface UserCleanerInteractor {

  suspend  fun clear(full: Boolean = false)
}
