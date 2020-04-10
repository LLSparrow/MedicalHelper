package com.llsparrow.healthassistant.core_base_impl.resources

import android.content.Context
import com.llsparrow.core_base_api.resources.ResourceManager
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceManagerImpl @Inject
//constructor(private val contextManager: ContextManager) : ResourceManager {
constructor(private val context: Context) : ResourceManager {

    override fun getString(resource: Int): String {
        return context.getString(resource)
    }
}