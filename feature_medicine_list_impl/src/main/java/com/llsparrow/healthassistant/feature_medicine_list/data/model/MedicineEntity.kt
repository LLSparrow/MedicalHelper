package com.llsparrow.healthassistant.feature_medicine_list.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

@Entity(tableName = "medicine")
@Fts4
data class MedicineEntity(

    @ColumnInfo(name = "medicineId") var medicineId: String,

    @ColumnInfo(name = "title") var title: String,

    @ColumnInfo(name = "description") var description: String
)