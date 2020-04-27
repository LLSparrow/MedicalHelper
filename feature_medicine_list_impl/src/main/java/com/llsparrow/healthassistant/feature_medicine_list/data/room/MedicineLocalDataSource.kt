package com.llsparrow.healthassistant.feature_medicine_list.data.room

import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_medicine_list.data.MedicineDataSource
import com.llsparrow.healthassistant.feature_medicine_list.data.room.dao.MedicineDao
import com.llsparrow.healthassistant.feature_medicine_list.data.room.mapper.MedicineToMedicineEntityMapper
import com.llsparrow.healthassistant.feature_medicine_list.data.room.mapper.MedicineWithImageToMedicineEntityMapper
import com.llsparrow.healthassistant.feature_medicine_list.domain.model.Medicine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@FeatureScope
class MedicineLocalDataSource @Inject constructor(
    private val mapper: MedicineToMedicineEntityMapper,
    private val mapperWithImage: MedicineWithImageToMedicineEntityMapper,
    private val medicineDao: MedicineDao
) : MedicineDataSource {
    override suspend fun getMedicineByTitle(title: String): Medicine? {
        return mapperWithImage.reverse(medicineDao.getMedicineByTitle(title))
    }

    override suspend fun deleteMedicine(medicine: Medicine) {
        medicineDao.deleteEntity(mapper.map(medicine))
    }

    override suspend fun addOrUpdateMedicine(medicine: Medicine) {
        medicineDao.insertOrUpdate(mapper.map(medicine))
    }

    override suspend fun getMedicineList(): List<Medicine> {
        return mapperWithImage.reverseList(medicineDao.getMedicineList())
    }

}