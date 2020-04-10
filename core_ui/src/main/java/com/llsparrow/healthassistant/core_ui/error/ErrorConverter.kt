package com.llsparrow.healthassistant.core_ui.error

import com.llsparrow.core_base_api.mapper.OneWayMapper
import com.llsparrow.core_network_api.data.exception.Alert
import com.llsparrow.core_network_api.data.exception.AlertType
import com.llsparrow.core_network_api.data.exception.ServerException
import com.llsparrow.core_network_api.data.exception.SessionExpiredException
import com.llsparrow.healthassistant.core_ui.R
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class ErrorConverter constructor() :
    OneWayMapper<Throwable, Alert>() {

    companion object {
        private const val UNEXPECTED_ERROR = "Unexpected error"
    }

    override fun map(item: Throwable): Alert {
//        return when {
//            item is ServerException -> item.alert ?: Alert(
//                description = item.message
//                    ?: UNEXPECTED_ERROR
//            )
//            isInternetConnectionError(item) -> Alert(
//                description = resourceManager.getString(
//                    R.string.no_internet_connection
//                )
//            )
//            item is SessionExpiredException -> Alert(
//                description = resourceManager.getString(
//                    R.string.session_not_available
//                )
//            )
//            else -> Alert(
//                description = item.message ?: UNEXPECTED_ERROR,
//                type = AlertType.TOAST
//            )
//        }
        return when {
            item is ServerException -> item.alert ?: Alert(
                description = item.message
                    ?: UNEXPECTED_ERROR, exception = item
            )
            isInternetConnectionError(item) -> Alert(descriptionRes = R.string.no_internet_connection, exception = item)
            item is SessionExpiredException -> Alert(descriptionRes = R.string.session_not_available, exception = item)
            else -> Alert(description = UNEXPECTED_ERROR, type = AlertType.TOAST, exception = item)
        }
    }

    private fun isInternetConnectionError(throwable: Throwable) =
        throwable is UnknownHostException ||
                throwable is SocketTimeoutException
}