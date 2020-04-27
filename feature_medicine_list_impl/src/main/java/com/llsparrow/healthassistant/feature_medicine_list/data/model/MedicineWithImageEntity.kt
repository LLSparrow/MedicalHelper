package com.llsparrow.healthassistant.feature_medicine_list.data.model

import androidx.room.Embedded
import androidx.room.Relation

class MedicineWithImageEntity {
    @Embedded
    lateinit var entry: MedicineEntity

    @Relation(parentColumn = "id", entityColumn = "medicine_id")
    var image: MedicineImageEntity? = null
}