package com.llsparrow.healthassistant.core_base_impl.di

import com.llsparrow.core_base_api.clipboard.Clipboard
import com.llsparrow.core_base_api.date.TimeSource
import com.llsparrow.core_base_api.filesystem.FileInteractor
import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.core_base_api.navigation.NavigationController
import com.llsparrow.core_base_api.resources.ResourceManager
import com.llsparrow.healthassistant.core_base_impl.clipboard.ClipboardImpl
import com.llsparrow.healthassistant.core_base_impl.date.TimeSourceImpl
import com.llsparrow.healthassistant.core_base_impl.filesystem.FileInteractorImpl
import com.llsparrow.healthassistant.core_base_impl.io.AppDispatchersImpl
import com.llsparrow.healthassistant.core_base_impl.navigation.NavigationControllerImpl
import com.llsparrow.healthassistant.core_base_impl.resources.ResourceManagerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface CoreBaseModule {
//    @Singleton
//    @Binds
//    fun provideRxScheduler(rxScheduler: RxSchedulersImpl): RxSchedulers

    @Singleton
    @Binds
    fun provideTimeSource(timeSource: TimeSourceImpl): TimeSource

    @Singleton
    @Binds
    fun provideResourceManager(resourceManager: ResourceManagerImpl): ResourceManager

    @Singleton
    @Binds
    fun provideClipboard(clipboard: ClipboardImpl): Clipboard

    @Singleton
    @Binds
    fun provideFileInteractor(fileInteractor: FileInteractorImpl): FileInteractor

    @Singleton
    @Binds
    fun provideAppDispatchers(appDispatchers: AppDispatchersImpl): AppDispatchers

//    @Singleton
//    @Binds
//    fun provideNavigationController(navigationController: NavigationControllerImpl): NavigationController

//    @Singleton
//    @Binds
//    fun provideLocalizationApi(localization: LocalizationImpl): LocalizationApi

    companion object {

//        @Singleton
//        @Provides
//        fun provideAnalyticsGateway(appConfig: AppConfig): AnalyticsGateway? {
//            return if (appConfig.isDebugBuildType()) AnalyticsGatewayStub() else AnalyticsGatewayImpl(Answers.getInstance())
//        }
    }
}