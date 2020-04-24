package com.llsparrow.healthassistant.feature_medicine_list.data

import com.llsparrow.healthassistant.feature_medicine_list.domain.model.Medicine
import kotlinx.coroutines.flow.Flow

interface MedicineDataSource {
    fun getMedicineByTitle(title: String):  Flow<Medicine>

    fun deleteMedicine(medicine: Medicine)

    fun addOrUpdateMedicine(medicine: Medicine)

    fun getMedicineList(): List<Medicine>
}