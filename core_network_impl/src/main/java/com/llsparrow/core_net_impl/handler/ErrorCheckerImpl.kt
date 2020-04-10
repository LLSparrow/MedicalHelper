package com.llsparrow.core_net_impl.handler

import com.google.gson.Gson
import com.llsparrow.core_base_api.resources.ResourceManager
import com.llsparrow.core_network_api.data.exception.ErrorServerException
import com.llsparrow.core_network_api.data.exception.ServerException
import com.llsparrow.core_network_api.data.exception.WarningServerException
import com.llsparrow.core_network_api.data.response.BaseResponse
import com.llsparrow.core_network_api.data.response.Status
import com.llsparrow.core_network_api.data.response.getErrorMessage
import com.llsparrow.core_network_api.errors.ErrorHandler
import com.llsparrow.core_net_impl.R
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Check response status at the network layer
 * <p>
 *     If response status == [0] -> request finish success
 *     If response status == [1] -> request fail by user input (account already exists, wrong sms code etc)
 *     If response status == [2, 3, 4, 5] -> request fail on server
 */
@Singleton
class ErrorCheckerImpl @Inject constructor(
    private val gson: Gson,
    private val resourceManager: ResourceManager,
    private val errorHandlers: Set<@JvmSuppressWildcards ErrorHandler>
) : ErrorChecker {

    companion object {
        private const val SUCCESS = 0
        private const val USER_ERROR = 1
    }

    override fun throwExceptionIfError(jsonString: String?) {
        parseBaseResponse(jsonString)?.let { response ->
            extractExceptionIfError(response)?.let { throw it }
        }
    }

    private fun parseBaseResponse(jsonString: String?): BaseResponse<*>? =
        gson.fromJson(jsonString, BaseResponse::class.java)

    private fun extractExceptionIfError(baseResponse: BaseResponse<*>): ServerException? {
        var errorServerException: ServerException? = null
        val status = baseResponse.status

        when {
            status.code == USER_ERROR -> errorServerException = createUserError(status)
            status.code != SUCCESS -> {
                errorServerException = ErrorServerException(
                    status.code,
                    status.errorCode,
                    resourceManager.getString(R.string.something_went_wrong)
                )
            }
        }
        return errorServerException
    }

    private fun createUserError(status: Status): ServerException {
        val errorHandler = errorHandlers.firstOrNull { handler -> handler.canHandle(status) }
        return errorHandler?.retrieveError(status) ?: WarningServerException(
            status.code,
            status.errorCode,
            status.getErrorMessage()
        )
    }

}