package com.oldsenior.core_net_api.data.exception

import com.oldsenior.ella.core_ui.alert.Alert
import java.io.IOException

sealed class ServerException(
    val code: Int,
    val errorCode: Int,
    message: String,
    val alert: Alert? = null
) : IOException(message)

class ErrorServerException(
    code: Int,
    errorCode: Int,
    message: String
) : ServerException(code, errorCode, message)

class WarningServerException(
    code: Int,
    errorCode: Int,
    message: String
) : ServerException(code, errorCode, message)
