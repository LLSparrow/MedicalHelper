package com.llsparrow.core_base_api.domain.interactor

import com.llsparrow.core_base_api.domain.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow

abstract class Interactor<in P> {
    protected abstract val scope: CoroutineScope

    @ExperimentalCoroutinesApi
    operator fun invoke(params: P, timeoutMs: Long = defaultTimeoutMs): Flow<InvokeStatus> {
        val channel = ConflatedBroadcastChannel<InvokeStatus>(
            InvokeIdle
        )
        scope.launch {
            try {
                withTimeout(timeoutMs) {
                    channel.send(InvokeStarted)
                    try {
                        doWork(params)
                        channel.send(InvokeSuccess)
                    } catch (t: Throwable) {
                        channel.send(InvokeError(t))
                    }
                }
            } catch (t: TimeoutCancellationException) {
                channel.send(InvokeTimeout)
            }
        }
        return channel.asFlow()
    }

    suspend fun executeSync(params: P) = withContext(scope.coroutineContext) { doWork(params) }

    protected abstract suspend fun doWork(params: P)

    companion object {
        private val defaultTimeoutMs = TimeUnit.MINUTES.toMillis(5)
    }
}

operator fun Interactor<Unit>.invoke() = invoke(Unit)