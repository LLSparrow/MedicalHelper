package com.llsparrow.core_util.concurrency

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


suspend fun <T> runIO(block: () -> T): T =
    withContext(Dispatchers.IO) { block.invoke() }