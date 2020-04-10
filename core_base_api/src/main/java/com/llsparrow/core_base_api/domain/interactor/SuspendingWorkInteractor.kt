package com.llsparrow.core_base_api.domain.interactor

import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

abstract class SuspendingWorkInteractor<P : Any, T : Any> : ObservableInteractor<T> {
    private val channel = ConflatedBroadcastChannel<T>()

    suspend operator fun invoke(params: P) = channel.send(doWork(params))

    abstract suspend fun doWork(params: P): T

    override fun observe(): Flow<T> = channel.asFlow()
}