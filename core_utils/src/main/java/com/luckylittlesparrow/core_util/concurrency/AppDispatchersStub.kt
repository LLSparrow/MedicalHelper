package com.luckylittlesparrow.core_util.concurrency

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * @author Gusev Andrei
 * @since  1.0
 */
class AppDispatchersStub : AppDispatchers {

    override val IO: CoroutineDispatcher = Dispatchers.Unconfined

    override val Main: CoroutineDispatcher = Dispatchers.Unconfined

    override val Default: CoroutineDispatcher = Dispatchers.Unconfined
}