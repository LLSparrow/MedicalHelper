package com.oldsenior.ella.medicinelist.model

data class Medication(
        val id: Long,
        val name: String,
        val description: String,
        val avatarUrl: String?
)