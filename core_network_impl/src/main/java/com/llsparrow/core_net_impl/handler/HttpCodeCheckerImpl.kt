package com.llsparrow.core_net_impl.handler

import com.llsparrow.core_base_api.resources.ResourceManager
import com.llsparrow.core_network_api.data.exception.ErrorServerException
import com.llsparrow.core_net_impl.R
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HttpCodeCheckerImpl @Inject constructor(
    private val resourceManager: ResourceManager
) : HttpCodeChecker {
    override fun throwExceptionIfFail(httpCode: Int) {
        if (httpCode != 200) throw ErrorServerException(
            httpCode,
            httpCode,
            resourceManager.getString(R.string.something_went_wrong)
        )
    }
}