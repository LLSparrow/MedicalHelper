package com.llsparrow.healthassistant.feature_medicine_list.data.room.mapper

import com.llsparrow.core_base_api.mapper.Mapper
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineEntity
import com.llsparrow.healthassistant.feature_medicine_list.domain.model.Medicine
import javax.inject.Inject

@FeatureScope
class MedicineToMedicineEntityMapper @Inject constructor() : Mapper<Medicine, MedicineEntity>() {
    override suspend fun reverse(item: MedicineEntity): Medicine {
        return Medicine(
            id = item.id,
            title = item.title,
            description = item.description,
            amount = item.amount,
            expirationDate = item.expirationDate
        )
    }

    override suspend fun map(item: Medicine): MedicineEntity {
        return MedicineEntity(
            id = item.id,
            title = item.title,
            description = item.description,
            amount = item.amount,
            expirationDate = item.expirationDate
        )
    }
}