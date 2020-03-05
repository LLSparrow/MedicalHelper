package com.luckylittlesparrow.healthassistant.authlib.data.mapper

import com.google.firebase.auth.FirebaseUser
import com.luckylittlesparrow.core_base_api.mapper.OneWayMapper
import com.luckylittlesparrow.healthassistant.feature_account_api.model.User

/**
 * @author Gusev Andrei
 * @since  1.0
 */
class FirebaseUserToAppUserMapper : OneWayMapper<FirebaseUser, User>() {

    override fun map(item: FirebaseUser): User {
        return User(item.uid)
    }
}