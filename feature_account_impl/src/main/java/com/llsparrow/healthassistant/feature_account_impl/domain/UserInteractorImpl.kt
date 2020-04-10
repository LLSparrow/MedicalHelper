package com.llsparrow.healthassistant.feature_account_impl.domain

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_account_api.domain.UserInteractor
import com.llsparrow.healthassistant.feature_account_api.domain.model.User
import com.llsparrow.healthassistant.feature_account_impl.di.qualifier.DataSource
import com.llsparrow.healthassistant.feature_account_impl.di.qualifier.HeapSource
import io.reactivex.Completable
import io.reactivex.Maybe
import javax.inject.Inject

@FeatureScope
class UserInteractorImpl @Inject constructor(
    @param:DataSource private val userDataRepository: UserRepository,
    @param:HeapSource private val heapUserRepository: UserRepository
) : UserInteractor {

    override fun getUser(fromCache: Boolean): Maybe<User> {
        return heapUserRepository.getUser(fromCache)
            .switchIfEmpty(userDataRepository.getUser(fromCache))
    }

    override fun saveUser(userModel: User): Completable {
        return heapUserRepository.clear()
            .andThen(Completable.defer { userDataRepository.saveUser(userModel) })
    }

    override fun updateUser(userModel: User): Completable {
        return heapUserRepository.updateUser(userModel)
            .andThen(Completable.defer { userDataRepository.updateUser(userModel) })
    }

    override fun clear(): Completable {
        return heapUserRepository.clear()
            .andThen(userDataRepository.clear())
    }
}
