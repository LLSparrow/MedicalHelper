//package com.llsparrow.healthassistant.core_base_impl.analytics
//
//
//class AnalyticsGatewayImpl(private val answers: Answers) : AnalyticsGateway {
//    override fun sendEvent(analyticsEvent: AnalyticsEvent) {
//        val customEvent = CustomEvent(analyticsEvent.name)
//            .putCustomAttribute("value", analyticsEvent.value)
//        answers.logCustom(customEvent)
//    }
//}