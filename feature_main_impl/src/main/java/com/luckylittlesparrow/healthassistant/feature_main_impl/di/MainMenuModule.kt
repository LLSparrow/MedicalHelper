package com.luckylittlesparrow.healthassistant.feature_main_impl.di

import com.luckylittlesparrow.healthassistant.feature_main_impl.navigation.MainMenuRouter
import org.koin.dsl.module

/**
 * @author Gusev Andrei
 * @since  1.0
 */
val mainModule = module {

    single { MainMenuRouter(get()) }
}
