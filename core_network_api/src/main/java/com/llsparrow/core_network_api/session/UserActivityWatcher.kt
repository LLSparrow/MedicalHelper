package com.llsparrow.core_network_api.session

import androidx.appcompat.app.AppCompatActivity

interface UserActivityWatcher {

    fun init(activity: AppCompatActivity)

    fun onUserInteracted()
}