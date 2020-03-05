package com.luckylittlesparrow.healthassistant.core_ui.di

import com.luckylittlesparrow.healthassistant.core_ui.error.ErrorConverter
import org.koin.dsl.module

/**
 * @author Gusev Andrei
 * @since  1.0
 */

val coreUIModule = module {
    single { ErrorConverter(resourceManager = get()) }
}