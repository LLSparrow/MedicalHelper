package com.llsparrow.healthassistant.feature_authentication_impl.data.service

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

/**
 * @author Gusev Andrei
 * @since  1.0
 */
interface AuthService {
    fun signIn(): FirebaseUser?

    fun signInAnonymously(): Task<AuthResult?>

    fun signUp(email: String, password: String): Task<AuthResult>

    fun signIn(email: String, password: String): Task<AuthResult>

    fun signOut()

    fun currentUser(): FirebaseUser?

    fun signInWithCredential(credential: AuthCredential): Task<AuthResult>
}