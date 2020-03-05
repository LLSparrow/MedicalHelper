package com.luckylittlesparrow.healthassistant.authlib.domain

import android.content.Intent
import com.luckylittlesparrow.healthassistant.feature_account_api.model.User

/**
 * @author Gusev Andrei
 * @since  1.0
 */
interface AuthRepository {

    suspend fun isAuthenticated(): Boolean

    suspend fun signInAnonymously(): Boolean

    suspend fun signUp(username: String, email: String, password: String): User?

    suspend fun signOut(): Boolean

    suspend fun signInWithGoogle(data: Intent?): User?

    suspend fun signIn(username: String, password: String): User?
}