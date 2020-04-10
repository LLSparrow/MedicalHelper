package com.llsparrow.core_network_api.errors

import com.llsparrow.core_network_api.data.exception.ServerException
import com.llsparrow.core_network_api.data.response.Status

interface ErrorHandler {
    fun canHandle(status: Status): Boolean

    fun retrieveError(status: Status): ServerException
}