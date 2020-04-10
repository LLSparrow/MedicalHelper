package com.llsparrow.healthassistant.feature_account_impl.data

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_di.source.Local
import com.llsparrow.healthassistant.core_di.source.Remote
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_account_impl.domain.UserRepository
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

@FeatureScope
class UserRepositoryImpl @Inject constructor(
    @param:Remote private val remoteDataSource: UserDataSource,
    @param:Local private val localDataSource: UserDataSource
) : UserRepository {
    override fun getUser(fromCache: Boolean): Maybe<User> {
        return if (fromCache) {
            localDataSource.getUser()
        } else {
            remoteDataSource.getUser()
                .flatMap {
                    saveUser(it)
                        .toSingleDefault(it)
                        .toMaybe()
                }
        }
    }

    override fun saveUser(userModel: User): Completable {
        return localDataSource.saveUser(userModel)
    }

    override fun updateUser(userModel: User): Completable {
        return remoteDataSource.saveUser(userModel)
            .andThen(Completable.defer { saveUser(userModel) })
    }

    override fun clear(): Completable {
        return localDataSource.clear()
    }
}
