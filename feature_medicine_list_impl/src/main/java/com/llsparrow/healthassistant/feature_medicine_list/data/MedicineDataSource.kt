package com.llsparrow.healthassistant.feature_medicine_list.data

import com.llsparrow.healthassistant.feature_medicine_list.domain.model.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineDataSource {
    suspend fun getMedicineByTitle(title: String): Medicine?

    suspend fun deleteMedicine(medicine: Medicine)

    suspend fun addOrUpdateMedicine(medicine: Medicine)

    suspend fun getMedicineList(): List<Medicine>
}