package com.llsparrow.healthassistant.feature_medicine_list.data.room.mapper

import com.llsparrow.core_base_api.mapper.Mapper
import com.llsparrow.healthassistant.core_di.FeatureScope
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineEntity
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineImageEntity
import com.llsparrow.healthassistant.feature_medicine_list.data.model.MedicineWithImageEntity
import com.llsparrow.healthassistant.feature_medicine_list.domain.model.Medicine
import javax.inject.Inject

@FeatureScope
class MedicineWithImageToMedicineEntityMapper @Inject constructor() : Mapper<Medicine, MedicineWithImageEntity>() {
    override suspend fun reverse(item: MedicineWithImageEntity): Medicine {
        return Medicine(
            id = item.entry.id,
            title = item.entry.title,
            description = item.entry.description,
            amount = item.entry.amount,
            expirationDate = item.entry.expirationDate,
            logoUri = item.image?.path
        )
    }

    override suspend fun map(item: Medicine): MedicineWithImageEntity {
        val image = item.logoUri?.let {
            MedicineImageEntity(
                medicineId = item.id,
                path = it
            )
        }
        val medicine = MedicineEntity(
            id = item.id,
            title = item.title,
            description = item.description,
            amount = item.amount,
            expirationDate = item.expirationDate
        )

        return MedicineWithImageEntity().apply {
            entry = medicine
            this.image = image
        }
    }

}