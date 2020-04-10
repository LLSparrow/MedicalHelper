package com.llsparrow.healthassistant.di

import android.app.Application
import android.content.Context
import com.llsparrow.core_base_api.appinitializers.AppInitializer
import com.llsparrow.core_base_api.navigation.NavigationController
import com.llsparrow.healthassistant.CoreApplication
import com.llsparrow.healthassistant.appinitializers.LoggerInitializer
import com.llsparrow.healthassistant.config.local.AppBuildConfig
import dagger.Binds
import dagger.Module
import com.llsparrow.healthassistant.config.remote.FirebaseConfig
import com.llsparrow.healthassistant.core_base_impl.navigation.NavigationControllerImpl
import com.llsparrow.healthassistant.core_feature_toggle_api.config.AppConfig
import com.llsparrow.healthassistant.core_feature_toggle_api.remote.RemoteConfigRepository
import dagger.multibindings.IntoSet
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun provideApplication(application: CoreApplication): Application

    @Singleton
    @Binds
    abstract fun provideContext(application: CoreApplication): Context

    @Singleton
    @Binds
    abstract fun provideRemoteConfigRepository(config: FirebaseConfig): RemoteConfigRepository

    @Singleton
    @Binds
    abstract fun provideAppBuildConfig(appBuildConfig: AppBuildConfig): AppConfig

    @Binds
    @IntoSet
    abstract fun provideLoggerInitializer(bind: LoggerInitializer): AppInitializer

    @Singleton
    @Binds
    abstract fun provideNavigationController(navigationController: NavigationControllerImpl): NavigationController
//
//    @Singleton
//    @Binds
//    abstract fun provideLanguageFeatureAppDependencies(dependencies: LanguageFeatureAppDependenciesImpl): LanguageFeatureAppDependencies
}