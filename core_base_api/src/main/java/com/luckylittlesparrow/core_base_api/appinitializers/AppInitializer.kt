package com.luckylittlesparrow.core_base_api.appinitializers

import android.app.Application

interface AppInitializer{
    fun init(application: Application)
}