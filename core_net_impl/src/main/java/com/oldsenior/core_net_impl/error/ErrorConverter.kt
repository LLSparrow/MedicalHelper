package com.oldsenior.core_net_impl.error

import com.oldsenior.core_base_api.mapper.OneWayMapper
import com.oldsenior.core_base_api.resources.ResourceManager
import com.oldsenior.core_net_api.data.exception.ServerException
import com.oldsenior.core_net_api.data.exception.SessionExpiredException
import com.oldsenior.ella.core_ui.R
import com.oldsenior.ella.core_ui.alert.Alert
import com.oldsenior.ella.core_ui.alert.AlertType
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ErrorConverter @Inject constructor(private val resourceManager: ResourceManager) :
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