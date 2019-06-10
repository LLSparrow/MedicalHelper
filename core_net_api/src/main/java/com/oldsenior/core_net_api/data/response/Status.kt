package com.oldsenior.core_net_api.data.response

import com.google.gson.annotations.SerializedName

data class Status(
        @SerializedName("code")
        var code: Int = 0,
        @SerializedName("errorCode")
        var errorCode: Int = 0,
        @SerializedName("error")
        var error: String? = null,
        @SerializedName("warning")
        var warning: String? = null
)