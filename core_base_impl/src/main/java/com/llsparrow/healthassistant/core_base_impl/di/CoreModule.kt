//package com.llsparrow.healthassistant.core_base_impl.di
//
//import com.llsparrow.core_base_api.navigation.NavigationController
//import com.llsparrow.core_base_api.resources.ResourceManager
//import com.llsparrow.healthassistant.core_base_impl.navigation.NavigationControllerImpl
//import com.llsparrow.healthassistant.core_base_impl.resources.ResourceManagerImpl
//import org.koin.dsl.module
//
//val coreModule = module {
//    single<ResourceManager> { ResourceManagerImpl(context = get()) }
//    single<NavigationController>{ NavigationControllerImpl() }
//}