package com.oldsenior.ella.medicinelist

import android.content.Context
import android.content.Intent

interface MedicalListMenuRouter {
    fun startMedicalListMenuActivity(context: Context, intent: Intent)
}