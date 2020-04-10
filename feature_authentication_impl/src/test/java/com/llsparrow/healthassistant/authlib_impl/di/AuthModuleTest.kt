//package com.luckylittlesparrow.healthassistant.authlib_impl.di
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import com.luckylittlesparrow.healthassistant.authlib.di.authModule
//import org.junit.Rule
//import org.junit.Test
//import org.koin.core.context.startKoin
//import org.koin.test.check.checkModules
//
///**
// * @author Gusev Andrei
// * @since  1.0
// */
//class AuthModuleTest {
//    @get:Rule
//    var task = InstantTaskExecutorRule()
//    @Test
//    fun dependencies() {
//        startKoin {
//            modules(authModule)
//        }.checkModules()
//    }
//}