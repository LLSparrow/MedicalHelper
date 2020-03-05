package com.luckylittlesparrow.core_util.concurrency

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Gusev Andrei
 * @since  1.0
 */
class AppDispatchersImpl : AppDispatchers {

    override val IO: CoroutineDispatcher = Dispatchers.IO

    override val Main: CoroutineDispatcher = Dispatchers.Main

    override val Default: CoroutineDispatcher = Dispatchers.Default
}