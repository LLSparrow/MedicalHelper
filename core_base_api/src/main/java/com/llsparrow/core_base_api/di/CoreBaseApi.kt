package com.llsparrow.core_base_api.di

import android.content.Context
import com.llsparrow.core_base_api.clipboard.Clipboard
import com.llsparrow.core_base_api.resources.ResourceManager
import com.llsparrow.core_base_api.analytics.AnalyticsGateway
import com.llsparrow.core_base_api.date.TimeSource
import com.llsparrow.core_base_api.filesystem.FileInteractor
import com.llsparrow.core_base_api.io.AppDispatchers
import com.llsparrow.core_base_api.navigation.NavigationController

interface CoreBaseApi {

   // fun analytics(): AnalyticsGateway

   // fun rxScheduler(): RxSchedulers

    fun context(): Context

    fun timeSource(): TimeSource

    fun appDispatchers(): AppDispatchers

    fun resourceManager(): ResourceManager

    fun clipboard(): Clipboard

    fun fileInteractor(): FileInteractor

    fun navigationController(): NavigationController

//
//    fun localizationApi(): LocalizationApi
}
