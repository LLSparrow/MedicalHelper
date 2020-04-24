package com.llsparrow.healthassistant.feature_medicine_list.data.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Fts4

@Entity(tableName = "medicine_fts")
@Fts4(contentEntity = MedicineEntity::class)
data class MedicineEntityFts(
    @ColumnInfo(name = "title") val title: String
)