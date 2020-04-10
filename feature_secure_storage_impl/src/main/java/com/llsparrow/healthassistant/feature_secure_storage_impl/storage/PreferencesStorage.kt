package com.llsparrow.healthassistant.feature_secure_storage_impl.storage

import android.content.Context
import com.llsparrow.healthassistant.core_di.FeatureScope
import javax.inject.Inject

@FeatureScope
class PreferencesStorage @Inject constructor(
    context: Context
) : Storage {

    companion object {
        private const val PREFERENCES_FILE = "secure_storage"
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE)

    override fun save(key: String, data: String) {
        preferences.edit()
            .putString(key, data)
            .apply()
    }

    override fun retrieve(key: String): String? {
        return preferences.getString(key, null)
    }

    override fun clear() {
        preferences.edit()
            .clear()
            .apply()
    }
}