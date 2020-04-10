package com.llsparrow.healthassistant.feature_account_impl.data

import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import io.reactivex.Completable
import io.reactivex.Maybe

interface UserDataSource {

    fun getUser(): Maybe<User>

    fun saveUser(userModel: User): Completable

    fun clear(): Completable
}
