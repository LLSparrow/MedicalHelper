package com.llsparrow.core_base_api.domain

sealed class InvokeStatus

object InvokeIdle : InvokeStatus()

object InvokeStarted : InvokeStatus()

object InvokeSuccess : InvokeStatus()

object InvokeTimeout : InvokeStatus()

data class InvokeError(val throwable: Throwable) : InvokeStatus()
