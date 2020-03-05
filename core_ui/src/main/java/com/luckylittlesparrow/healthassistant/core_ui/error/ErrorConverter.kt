package com.luckylittlesparrow.healthassistant.core_ui.error

import com.luckylittlesparrow.core_base_api.mapper.OneWayMapper
import com.luckylittlesparrow.core_base_api.resources.ResourceManager
import com.luckylittlesparrow.core_net_api.data.exception.Alert
import com.luckylittlesparrow.core_net_api.data.exception.AlertType
import com.luckylittlesparrow.core_net_api.data.exception.ServerException
import com.luckylittlesparrow.core_net_api.data.exception.SessionExpiredException
import com.luckylittlesparrow.healthassistant.core_ui.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorConverter constructor(private val resourceManager: ResourceManager) :
    OneWayMapper<Throwable, Alert>() {

    companion object {
        private const val UNEXPECTED_ERROR = "Unexpected error"
    }

    override fun map(item: Throwable): Alert {
        return when {
            item is ServerException -> item.alert ?: Alert(
                description = item.message
                    ?: UNEXPECTED_ERROR
            )
            isInternetConnectionError(item) -> Alert(
                description = resourceManager.getString(
                    R.string.no_internet_connection
                )
            )
            item is SessionExpiredException -> Alert(
                description = resourceManager.getString(
                    R.string.session_not_available
                )
            )
            else -> Alert(
                description = item.message ?: UNEXPECTED_ERROR,
                type = AlertType.TOAST
            )
        }
    }

    private fun isInternetConnectionError(throwable: Throwable) =
        throwable is UnknownHostException ||
                throwable is SocketTimeoutException
}