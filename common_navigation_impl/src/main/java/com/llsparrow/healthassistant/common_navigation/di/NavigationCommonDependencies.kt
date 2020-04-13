package com.llsparrow.healthassistant.common_navigation.di

import com.llsparrow.healthassistant.common_navigation_api.navigation.core.NavigationController

interface NavigationCommonDependencies{
    fun navigationController(): NavigationController
}