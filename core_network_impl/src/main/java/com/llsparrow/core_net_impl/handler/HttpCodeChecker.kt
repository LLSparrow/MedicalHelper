package com.llsparrow.core_net_impl.handler

interface HttpCodeChecker {
    fun throwExceptionIfFail(httpCode: Int)
}