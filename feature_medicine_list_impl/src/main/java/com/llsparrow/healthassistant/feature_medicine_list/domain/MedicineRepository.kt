package com.llsparrow.healthassistant.feature_medicine_list.domain

import com.llsparrow.healthassistant.feature_medicine_list.domain.model.Medicine

interface MedicineRepository{
    suspend fun getMedicineByTitle(title: String): Medicine?

    suspend fun deleteMedicine(medicine: Medicine)

    suspend fun addOrUpdateMedicine(medicine: Medicine)

    suspend fun getMedicineList(): List<Medicine>
}