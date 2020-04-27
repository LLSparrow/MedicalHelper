package com.llsparrow.healthassistant.feature_medicine_list.data

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.core_di.source.Local
import com.llsparrow.healthassistant.feature_medicine_list.domain.MedicineRepository
import com.llsparrow.healthassistant.feature_medicine_list.domain.model.Medicine
import javax.inject.Inject

@FeatureScope
class MedicineRepositoryImpl @Inject constructor(
    @Local private val localDataSource: MedicineDataSource
) : MedicineRepository {
    override suspend fun getMedicineByTitle(title: String): Medicine? {
        return localDataSource.getMedicineByTitle(title)
    }

    override suspend fun deleteMedicine(medicine: Medicine) {
        localDataSource.deleteMedicine(medicine)
    }

    override suspend fun addOrUpdateMedicine(medicine: Medicine) {
        localDataSource.addOrUpdateMedicine(medicine)
    }

    override suspend fun getMedicineList(): List<Medicine> {
        return localDataSource.getMedicineList()
    }

}