package com.llsparrow.healthassistant.feature_authentication_impl.data.service

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.llsparrow.healthassistant.core_di.FeatureScope
import javax.inject.Inject


@FeatureScope
class AuthServiceImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) : AuthService {
    override fun signIn(email: String, password: String): Task<AuthResult> =
        firebaseAuth.signInWithEmailAndPassword(email, password)

    override fun signInWithCredential(credential: AuthCredential) = firebaseAuth.signInWithCredential(credential)

    override fun currentUser(): FirebaseUser? = firebaseAuth.currentUser

    override fun signIn(): FirebaseUser? = firebaseAuth.currentUser

    override fun signInAnonymously(): Task<AuthResult?> = firebaseAuth.signInAnonymously()

    override fun signUp(email: String, password: String): Task<AuthResult> =
        firebaseAuth.createUserWithEmailAndPassword(email, password)

    override fun signOut() = firebaseAuth.signOut()

}