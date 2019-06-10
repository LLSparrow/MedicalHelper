package com.oldsenior.core_net_api.errors

import com.oldsenior.core_net_api.data.exception.ServerException
import com.oldsenior.core_net_api.data.response.Status

interface ErrorHandler {
    fun canHandle(status: Status): Boolean

    fun retrieveError(status: Status): ServerException
}