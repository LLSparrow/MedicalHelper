package com.llsparrow.healthassistant.core_base_impl.io

import com.llsparrow.core_base_api.io.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDispatchersImpl @Inject constructor() : AppDispatchers {

    override val IO: CoroutineDispatcher = Dispatchers.IO

    override val Main: CoroutineDispatcher = Dispatchers.Main

    override val Default: CoroutineDispatcher = Dispatchers.Default
}