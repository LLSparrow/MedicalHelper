package com.llsparrow.healthassistant.core_base_impl.analytics

import com.llsparrow.core_base_api.analytics.AnalyticsEvent
import com.llsparrow.core_base_api.analytics.AnalyticsGateway

class AnalyticsGatewayStub : AnalyticsGateway {
    override fun sendEvent(analyticsEvent: AnalyticsEvent) {
        // do nothing
    }
}