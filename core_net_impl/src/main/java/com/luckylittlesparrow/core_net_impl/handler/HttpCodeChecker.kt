package com.luckylittlesparrow.core_net_impl.handler

interface HttpCodeChecker {
    fun throwExceptionIfFail(httpCode: Int)
}