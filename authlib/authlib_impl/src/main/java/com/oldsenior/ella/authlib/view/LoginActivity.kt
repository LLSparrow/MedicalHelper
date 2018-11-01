package com.oldsenior.ella.authlib.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.oldsenior.ella.authlib.router.AuthRouter
import com.oldsenior.ella.corelib.router.FlowResult

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun onLoginSuccess() {
        AuthRouter.sendResult(FlowResult.OK)
    }
}
