package com.llsparrow.healthassistant.feature_account_impl.di

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_di.source.Local
import com.llsparrow.healthassistant.core_di.source.Remote
import com.llsparrow.healthassistant.feature_account_api.domain.UserCleanerInteractor
import com.llsparrow.healthassistant.feature_account_api.domain.UserInteractor
import com.llsparrow.healthassistant.feature_account_impl.data.UserDataSource
import com.llsparrow.healthassistant.feature_account_impl.data.UserRepositoryImpl
import com.llsparrow.healthassistant.feature_account_impl.data.heap.HeapUserRepository
import com.llsparrow.healthassistant.feature_account_impl.data.remote.RemoteUserDataSource
import com.llsparrow.healthassistant.feature_account_impl.data.room.LocalUserDataSource
import com.llsparrow.healthassistant.core_di.qualifier.DataSource
import com.llsparrow.healthassistant.core_di.qualifier.HeapSource
import com.llsparrow.healthassistant.feature_account_impl.domain.UserCleanerInteractorImpl
import com.llsparrow.healthassistant.feature_account_impl.domain.UserInteractorImpl
import com.llsparrow.healthassistant.feature_account_impl.domain.UserRepository
import dagger.Binds
import dagger.Module

@Module
interface AccountFeatureModule {
    @FeatureScope
    @Binds
    fun provideUserInteractor(userInteractor: UserInteractorImpl): UserInteractor

    @FeatureScope
    @Binds
    fun provideUserCleanerInteractor(userInteractor: UserCleanerInteractorImpl): UserCleanerInteractor

    @FeatureScope
    @Binds
    @Remote
    fun provideRemoteUserDataSource(datasource: RemoteUserDataSource): UserDataSource

    @FeatureScope
    @Binds
    @Local
    fun provideLocalUserDataSource(datasource: LocalUserDataSource): UserDataSource

    @FeatureScope
    @DataSource
    @Binds
    fun provideUserRepository(repository: UserRepositoryImpl): UserRepository

    @FeatureScope
    @Binds
    @HeapSource
    fun provideHeapUserRepository(repository: HeapUserRepository): UserRepository
}