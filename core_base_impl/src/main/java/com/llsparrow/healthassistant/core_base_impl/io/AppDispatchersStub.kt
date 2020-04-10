package com.llsparrow.healthassistant.core_base_impl.io

import com.llsparrow.core_base_api.io.AppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppDispatchersStub : AppDispatchers {

    override val IO: CoroutineDispatcher = Dispatchers.Unconfined

    override val Main: CoroutineDispatcher = Dispatchers.Unconfined

    override val Default: CoroutineDispatcher = Dispatchers.Unconfined
}