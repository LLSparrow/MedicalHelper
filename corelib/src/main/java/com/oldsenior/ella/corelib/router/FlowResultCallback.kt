package com.oldsenior.ella.corelib.router

import android.os.Bundle

interface FlowResultCallback {
    fun sendResult(flowResult: FlowResult)
}