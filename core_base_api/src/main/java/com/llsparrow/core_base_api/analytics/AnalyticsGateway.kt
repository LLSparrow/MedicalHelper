package com.llsparrow.core_base_api.analytics

interface AnalyticsGateway {
    fun sendEvent(analyticsEvent: AnalyticsEvent)
}