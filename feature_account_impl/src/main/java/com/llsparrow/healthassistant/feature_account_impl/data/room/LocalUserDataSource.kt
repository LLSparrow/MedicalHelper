package com.llsparrow.healthassistant.feature_account_impl.data.room

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_account_impl.data.UserDataSource
import com.llsparrow.healthassistant.feature_account_impl.data.room.dao.UserDao
import javax.inject.Inject

@FeatureScope
class LocalUserDataSource @Inject
constructor(
    //private val userDao: UserDao
//    private val userEntityMapper: UserModelToUserEntityMapper
) : UserDataSource {

    override suspend fun getUser(): User? {
        throw NotImplementedError()
//        return userDao.user
//            .map { userEntityMapper.reverse(it) }
    }

    override suspend fun updateUser(user: User) {
        throw NotImplementedError()
//        return Maybe.just(userModel)
//            .map { userEntityMapper.map(it) }
//            .flatMap { userEntity ->
//                userDao.user
//                    .map { localUser -> userEntity.copy(id = localUser.id) }
//                    .defaultIfEmpty(userEntity)
//            }
//            .flatMapCompletable { userEntity -> userDao.insert(userEntity) }
    }

    override suspend fun clear() {
        throw NotImplementedError()
    }
}
