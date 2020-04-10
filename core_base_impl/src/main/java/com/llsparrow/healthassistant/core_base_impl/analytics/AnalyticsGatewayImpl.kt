//package com.llsparrow.healthassistant.core_base_impl.analytics
//
//import com.crashlytics.android.answers.Answers
//import com.crashlytics.android.answers.CustomEvent
//import com.llsparrow.core_base_api.analytics.AnalyticsEvent
//import com.llsparrow.core_base_api.analytics.AnalyticsGateway
//import jp.co.soramitsu.core_base_api.analytics.AnalyticsEvent
//import jp.co.soramitsu.core_base_api.analytics.AnalyticsGateway
//
//class AnalyticsGatewayImpl(private val answers: Answers) : AnalyticsGateway {
//    override fun sendEvent(analyticsEvent: AnalyticsEvent) {
//        val customEvent = CustomEvent(analyticsEvent.name)
//            .putCustomAttribute("value", analyticsEvent.value)
//        answers.logCustom(customEvent)
//    }
//}