package com.luckylittlesparrow.core_net_api.errors

import com.luckylittlesparrow.core_net_api.data.exception.ServerException
import com.luckylittlesparrow.core_net_api.data.response.Status

interface ErrorHandler {
    fun canHandle(status: Status): Boolean

    fun retrieveError(status: Status): ServerException
}