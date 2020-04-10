package com.llsparrow.core_network_api.data

/**
 * @author Gusev Andrei
 * @since  1.0
 */

sealed class Result<out A : Any, out B : Any> {
    data class Success<A : Any>(val value: A) : Result<A, Nothing>()
    data class Error<B : Any>(val error: B) : Result<Nothing, B>()
}