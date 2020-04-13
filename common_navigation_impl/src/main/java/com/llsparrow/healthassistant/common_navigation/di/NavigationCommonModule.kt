package com.llsparrow.healthassistant.common_navigation.di

import com.llsparrow.healthassistant.common_navigation.auth.AuthLauncherImpl
import com.llsparrow.healthassistant.common_navigation.auth.AuthRouterImpl
import com.llsparrow.healthassistant.common_navigation.main.MainMenuRouterImpl
import com.llsparrow.healthassistant.common_navigation_api.navigation.auth.AuthLauncher
import com.llsparrow.healthassistant.common_navigation_api.navigation.auth.AuthRouter
import com.llsparrow.healthassistant.common_navigation_api.navigation.main.MainMenuRouter
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface NavigationCommonModule {

    @Singleton
    @Binds
    abstract fun provideAuthLauncher(authLauncher: AuthLauncherImpl): AuthLauncher

    @Singleton
    @Binds
    abstract fun provideAuthRouter(authRouter: AuthRouterImpl): AuthRouter

    @Singleton
    @Binds
    abstract fun provideMainMenuRouter(mainMenuRouter: MainMenuRouterImpl): MainMenuRouter
}