package com.llsparrow.healthassistant.config.remote

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.llsparrow.healthassistant.BuildConfig
import com.llsparrow.healthassistant.core_feature_toggle_api.remote.RemoteConfigRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseConfig @Inject constructor() : RemoteConfigRepository {

    private val remoteConfig = FirebaseRemoteConfig.getInstance()
    private val flowable: Flowable<Boolean>

    init {
        val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(BuildConfig.DEBUG)
                .build()
        remoteConfig.setConfigSettings(configSettings)
        flowable = Flowable.create({ emitter ->
                remoteConfig.fetch()
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                remoteConfig.activateFetched()
                            }
                            emitter.onNext(true)
                        }
                emitter.onNext(false)
        }, BackpressureStrategy.LATEST)
    }

    override fun getString(key: String): Single<String> {
        return flowable
                .filter { value -> value }
                .firstOrError()
                .map { remoteConfig.getString(key) }
    }

    override fun getBoolean(key: String): Single<Boolean> {
        return flowable
                .filter { value -> value }
                .firstOrError()
                .map { remoteConfig.getBoolean(key) }
    }
}