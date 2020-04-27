package com.llsparrow.core_base_api.io

import kotlinx.coroutines.CoroutineDispatcher

interface AppDispatchers {
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
    val Default: CoroutineDispatcher
}