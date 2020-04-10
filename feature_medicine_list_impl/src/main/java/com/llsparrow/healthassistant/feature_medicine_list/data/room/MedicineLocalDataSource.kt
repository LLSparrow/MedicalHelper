package com.llsparrow.healthassistant.feature_medicine_list.data.room

import com.llsparrow.healthassistant.feature_medicine_list.data.MedicineDataSource
import com.llsparrow.healthassistant.feature_medicine_list.domain.model.Medicine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MedicineLocalDataSource : MedicineDataSource {
    override fun getMedicineByTitle(title: String): Flow<Medicine> = flow {
        TODO("Not yet implemented")
    }

    override fun deleteMedicine(medicine: Medicine) {
        TODO("Not yet implemented")
    }

    override fun updateMedicine(medicine: Medicine) {
        TODO("Not yet implemented")
    }

    override fun getMedicineList(): List<Medicine> {
        TODO("Not yet implemented")
    }

}