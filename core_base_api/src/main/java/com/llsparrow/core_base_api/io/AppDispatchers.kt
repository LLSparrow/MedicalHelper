package com.llsparrow.core_base_api.io

import kotlinx.coroutines.CoroutineDispatcher

/**
 * @author Gusev Andrei
 * @since  1.0
 */
interface AppDispatchers {
    val IO: CoroutineDispatcher
    val Main: CoroutineDispatcher
    val Default: CoroutineDispatcher
}