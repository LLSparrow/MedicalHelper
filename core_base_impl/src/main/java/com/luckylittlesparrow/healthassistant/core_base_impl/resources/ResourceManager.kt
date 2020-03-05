package com.luckylittlesparrow.healthassistant.core_base_impl.resources

import android.content.Context
import com.luckylittlesparrow.core_base_api.resources.ResourceManager

class ResourceManagerImpl
//constructor(private val contextManager: ContextManager) : ResourceManager {
constructor(private val context: Context) : ResourceManager {

    override fun getString(resource: Int): String {
        return context.getString(resource)
    }
}