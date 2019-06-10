package com.oldsenior.core_net_api.data.response

fun Status.getErrorMessage(): String {
    val message = if (!error.isNullOrBlank()) error else warning
    return message ?: "Unexpected error"// TODO возвращать тип, а конвертить в текст в другом месте
}