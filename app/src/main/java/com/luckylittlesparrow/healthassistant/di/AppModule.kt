package com.luckylittlesparrow.healthassistant.di

import com.luckylittlesparrow.core_base_api.appinitializers.AppInitializer
import com.luckylittlesparrow.healthassistant.appinitializers.LoggerInitializer
import com.luckylittlesparrow.healthassistant.core_base_impl.appinitializers.AppInitializers
import org.koin.dsl.module

/**
 * @author Gusev Andrei
 * @since  1.0
 */
val appModule = module {
//    factory { AppInitializers(get()) }
//    factory<AppInitializer> { LoggerInitializer() }
}