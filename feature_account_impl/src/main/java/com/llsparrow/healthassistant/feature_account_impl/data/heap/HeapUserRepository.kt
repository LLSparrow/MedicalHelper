package com.llsparrow.healthassistant.feature_account_impl.data.heap

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_account_impl.domain.UserRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

@FeatureScope
class HeapUserRepository @Inject constructor() :
    UserRepository {

    private var userModel: User? = null

    override fun getUser(fromCache: Boolean): Maybe<User> {
        return if (fromCache) {
            Maybe.fromCallable { userModel }
        } else {
            Maybe.empty()
        }
    }

    override fun saveUser(userModel: User): Completable {
        return Completable.fromAction { this.userModel = userModel }
    }

    override fun updateUser(userModel: User): Completable {
        return Completable.fromAction { this.userModel = userModel }
    }

    override fun clear(): Completable {
        return Completable.fromAction { userModel = null }
    }
}
