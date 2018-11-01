package com.oldsenior.ella.medicinelist.view

import android.os.Bundle
import com.oldsenior.ella.corelib.view.BaseActivity

class MedicalListMenuActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
