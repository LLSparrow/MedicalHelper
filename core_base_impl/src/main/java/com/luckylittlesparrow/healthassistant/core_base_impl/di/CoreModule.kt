package com.luckylittlesparrow.healthassistant.core_base_impl.di

import com.luckylittlesparrow.core_base_api.navigation.NavigationController
import com.luckylittlesparrow.core_base_api.resources.ResourceManager
import com.luckylittlesparrow.healthassistant.core_base_impl.navigation.NavigationControllerImpl
import com.luckylittlesparrow.healthassistant.core_base_impl.resources.ResourceManagerImpl
import org.koin.dsl.module

val coreModule = module {
    single<ResourceManager> { ResourceManagerImpl(context = get()) }
    single<NavigationController>{ NavigationControllerImpl() }
}