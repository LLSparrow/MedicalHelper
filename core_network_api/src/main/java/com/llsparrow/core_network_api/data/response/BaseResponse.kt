package com.llsparrow.core_network_api.data.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("status")
    val status: Status,
    @SerializedName("data")
    val data: T
)