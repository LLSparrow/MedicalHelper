package com.luckylittlesparrow.core_util.di

import com.luckylittlesparrow.core_util.concurrency.AppDispatchers
import com.luckylittlesparrow.core_util.concurrency.AppDispatchersImpl
import org.koin.dsl.module

/**
 * @author Gusev Andrei
 * @since  1.0
 */
val coreUtilsModule = module {
    single<AppDispatchers> { AppDispatchersImpl() }
}